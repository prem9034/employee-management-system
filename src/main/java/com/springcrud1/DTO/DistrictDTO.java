package com.springcrud1.DTO;

import java.util.List;

public class DistrictDTO {
    private String name;
    private Long stateId;
private Long id;
private String vstateName;
    private List<CityDTO> cities;

    public List<CityDTO> getCities() {
        return cities;
    }

    public void setCities(List<CityDTO> cities) {
        this.cities = cities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVstateName() {
        return vstateName;
    }

    public void setVstateName(String vstateName) {
        this.vstateName = vstateName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
}
