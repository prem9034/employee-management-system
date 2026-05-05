package com.springcrud1.service;

import com.springcrud1.DTO.ApplyLeaveDTO;
import com.springcrud1.entity.LeaveBalance;
import com.springcrud1.entity.LeaveRequest;
import com.springcrud1.entity.LeaveType;
import com.springcrud1.repository.LeaveBalanceRepository;
import com.springcrud1.repository.LeaveRequestRepository;
import com.springcrud1.repository.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    // 🔥 APPLY LEAVE
    @Transactional
    public String applyLeave(ApplyLeaveDTO dto, Long employeeId) {

        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new RuntimeException("Invalid date range");
        }

        // overlap check
        List<LeaveRequest> overlaps =
                leaveRequestRepository.findOverlappingLeaves(
                        employeeId, dto.getStartDate(), dto.getEndDate());

        if (!overlaps.isEmpty()) {
            throw new RuntimeException("Leave already exists for these dates");
        }

        int days = (int) ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;

        LeaveType leaveType = leaveTypeRepository.findById(dto.getLeaveTypeId())
                .orElseThrow(() -> new RuntimeException("Invalid Leave Type"));

        int year = LocalDate.now().getYear();

        LeaveBalance balance = leaveBalanceRepository
                .findByEmployeeIdAndLeaveTypeIdAndYear(employeeId, dto.getLeaveTypeId(), year)
                .orElseThrow(() -> new RuntimeException("Leave balance not found"));

        if (balance.getRemaining() < days) {
            throw new RuntimeException("Insufficient balance");
        }

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployeeId(employeeId);
        leave.setLeaveType(leaveType);
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setTotalDays(days);
        leave.setReason(dto.getReason());
        leave.setStatus("PENDING");
        leave.setAppliedAt(LocalDateTime.now());

        leaveRequestRepository.save(leave);

        return "Leave Applied Successfully";
    }

    // 🔥 APPROVE
    @Transactional
    public String approveLeave(Long leaveId) {

        LeaveRequest leave = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        if (!leave.getStatus().equals("PENDING")) {
            throw new RuntimeException("Already processed");
        }

        int year = LocalDate.now().getYear();

        LeaveBalance balance = leaveBalanceRepository
                .findByEmployeeIdAndLeaveTypeIdAndYear(
                        leave.getEmployeeId(),
                        leave.getLeaveType().getId(),
                        year)
                .orElseThrow(() -> new RuntimeException("Balance not found"));

        balance.setUsed(balance.getUsed() + leave.getTotalDays());
        balance.setRemaining(balance.getRemaining() - leave.getTotalDays());

        leave.setStatus("APPROVED");

        return "Leave Approved";
    }

    // 🔥 REJECT
    public String rejectLeave(Long leaveId) {

        LeaveRequest leave = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        if (!leave.getStatus().equals("PENDING")) {
            throw new RuntimeException("Already processed");
        }

        leave.setStatus("REJECTED");

        return "Leave Rejected";
    }

    // 🔥 MY LEAVES
    public List<LeaveRequest> getMyLeaves(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }
}
