package com.springcrud1.controller;

import com.springcrud1.DTO.*;
import com.springcrud1.entity.City;
import com.springcrud1.entity.Country;
import com.springcrud1.entity.District;
import com.springcrud1.entity.State;
import com.springcrud1.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService service;

    // ✅ Country
    @PostMapping("/country")
    public Country saveCountry(@RequestBody CountryDTO dto) {
        return service.saveCountry(dto);
    }

    // ✅ State

    @PostMapping("/state")
    public StateDTO saveState(@RequestBody StateDTO dto) {
        return service.saveState(dto);
    }

    // ✅ District
    @PostMapping("/district")
    public DistrictDTO saveDistrict(@RequestBody DistrictDTO dto) {
        return service.saveDistrict(dto);
    }

    // ✅ City
    @PostMapping("/city")
    public CityDTO saveCity(@RequestBody CityDTO dto) {
        return service.saveCity(dto);
    }
    @GetMapping("/stateList/{countryId}")
    public List<StateDTO> getStates(@PathVariable Long countryId) {
        return service.getStatesByCountry(countryId);
    }

    @GetMapping("/districtList/{stateId}")
    public List<DistrictDTO> getDistricts(@PathVariable Long stateId) {
        return service.getDistrictsByState(stateId);
    }

    @GetMapping("/cityList/{districtId}")
    public List<CityDTO> getCities(@PathVariable Long districtId) {
        return service.getCitiesByDistrict(districtId);
    }
    @GetMapping("/country/{id}/full")
    public CountryFullResponseDTO getFullCountry(@PathVariable Long id) {
        return service.getFullCountry(id);
    }
}
