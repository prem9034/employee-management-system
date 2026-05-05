package com.springcrud1.repository;

import com.springcrud1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department, Long> {
}
