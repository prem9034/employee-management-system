package com.springcrud1.DTO;

public class EmployeeDTO {
    private Long id;
    private String vemployeeName;
    private String vdepartment;
    private String vcity;
    private String vemployeeMobile;
    private Long departmentId;
    private Long cityId;
    private String vemployeeEmail;

    public String getVemployeeEmail() {
        return vemployeeEmail;
    }

    public void setVemployeeEmail(String vemployeeEmail) {
        this.vemployeeEmail = vemployeeEmail;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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

    public String getVdepartment() {
        return vdepartment;
    }

    public void setVdepartment(String vdepartment) {
        this.vdepartment = vdepartment;
    }

    public String getVcity() {
        return vcity;
    }

    public void setVcity(String vcity) {
        this.vcity = vcity;
    }

    public String getVemployeeMobile() {
        return vemployeeMobile;
    }

    public void setVemployeeMobile(String vemployeeMobile) {
        this.vemployeeMobile = vemployeeMobile;
    }
}
