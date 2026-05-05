package com.springcrud1.repository;

import com.springcrud1.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employeeId = :empId " +
            "AND lr.status IN ('PENDING','APPROVED') " +
            "AND (lr.startDate <= :endDate AND lr.endDate >= :startDate)")
    List<LeaveRequest> findOverlappingLeaves(Long empId, LocalDate startDate, LocalDate endDate);

    List<LeaveRequest> findByEmployeeId(Long employeeId);
}