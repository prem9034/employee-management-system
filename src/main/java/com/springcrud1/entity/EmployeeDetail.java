package com.springcrud1.entity;

//import jakarta.persistence.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_details")
public class EmployeeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vemployeeName;
    private String vemployeeEmail;
    private String vemployee_mobile;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Column(name = "cemp_status")
    private String cempStatus;
    @Column(name="vemp_code" , unique = true)
    private String vempcode;

    public String getVempcode() {
        return vempcode;
    }

    public void setVempcode(String vempcode) {
        this.vempcode = vempcode;
    }

    public String getCempStatus() {
        return cempStatus;
    }

    public void setCempStatus(String cempStatus) {
        this.cempStatus = cempStatus;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVemployeeName() {
        return vemployeeName;
    }

    public void setVemployeeName(String vemployeeName) {
        this.vemployeeName = vemployeeName;
    }

    public String getVemployeeEmail() {
        return vemployeeEmail;
    }

    public void setVemployeeEmail(String vemployeeEmail) {
        this.vemployeeEmail = vemployeeEmail;
    }

    public String getVemployee_mobile() {
        return vemployee_mobile;
    }

    public void setVemployee_mobile(String vemployee_mobile) {
        this.vemployee_mobile = vemployee_mobile;
    }


}
