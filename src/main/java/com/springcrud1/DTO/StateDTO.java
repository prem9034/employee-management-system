package com.springcrud1.DTO;

import java.util.List;

public class StateDTO {
    private Long id;
    private String name;
    private Long countryId;
    private String countryName;
    private List<DistrictDTO> districts;

    public List<DistrictDTO> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictDTO> districts) {
        this.districts = districts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
