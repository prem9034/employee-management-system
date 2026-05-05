package com.springcrud1.DTO;

public class EmployeeListDTO {
    private String employeeName;
    private String email;
    private String mobile;

    private String department;
    private String city;
    private String district;
    private String state;
    private String country;

    public EmployeeListDTO(String employeeName, String email, String mobile,
                           String department, String city, String district,
                           String state, String country) {

        this.employeeName = employeeName;
        this.email = email;
        this.mobile = mobile;
        this.department = department;
        this.city = city;
        this.district = district;
        this.state = state;
        this.country = country;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
