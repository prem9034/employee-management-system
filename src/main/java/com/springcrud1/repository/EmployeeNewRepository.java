package com.springcrud1.repository;

import com.springcrud1.DTO.EmployeeListDTO;
import com.springcrud1.entity.EmployeeDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface EmployeeNewRepository extends  JpaRepository<EmployeeDetail, Long> {
    @Query("SELECT MAX(e.id) FROM EmployeeDetail e")
    Long getMaxId();
    @Query("""
SELECT new com.springcrud1.DTO.EmployeeListDTO(
    e.vemployeeName,
    e.vemployeeEmail,
    e.vemployee_mobile,
    d.vdepartmentName,
    c.name,
    dist.name,
    s.name,
    co.name
)
FROM EmployeeDetail e
LEFT JOIN e.department d
LEFT JOIN e.city c
LEFT JOIN c.district dist
LEFT JOIN dist.state s
LEFT JOIN s.country co
""")
    List<EmployeeListDTO> getEmployeeFullDetails();
}
