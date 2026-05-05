package com.springcrud1.repository;

import com.springcrud1.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
}
