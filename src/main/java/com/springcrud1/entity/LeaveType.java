package com.springcrud1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "leave_types")
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // CL, SL, EL

    @Column(name = "max_per_year")
    private Integer maxPerYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxPerYear() {
        return maxPerYear;
    }

    public void setMaxPerYear(Integer maxPerYear) {
        this.maxPerYear = maxPerYear;
    }
// getters & setters
}