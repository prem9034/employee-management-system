package com.springcrud1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "leave_balance")
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    private Integer year;

    private Integer total;

    private Integer used;

    private Integer remaining;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }
// getters & setters
}
