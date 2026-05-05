package com.springcrud1.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vdepartmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<EmployeeDetail> employeeDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVdepartmentName() {
        return vdepartmentName;
    }

    public void setVdepartmentName(String vdepartmentName) {
        this.vdepartmentName = vdepartmentName;
    }

    public List<EmployeeDetail> getEmployees() {
        return employeeDetails;
    }

    public void setEmployees(List<EmployeeDetail> employeeDetails) {
        this.employeeDetails = employeeDetails;
    }
}
