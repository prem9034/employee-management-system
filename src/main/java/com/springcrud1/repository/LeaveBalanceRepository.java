package com.springcrud1.repository;

import com.springcrud1.entity.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

    Optional<LeaveBalance> findByEmployeeIdAndLeaveTypeIdAndYear(
            Long employeeId, Long leaveTypeId, Integer year);
}
