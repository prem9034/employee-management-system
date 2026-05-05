package com.springcrud1.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<EmployeeDetail> employeeDetails;

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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
