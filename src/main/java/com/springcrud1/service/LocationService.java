package com.springcrud1.service;

import com.springcrud1.DTO.*;
import com.springcrud1.entity.City;
import com.springcrud1.entity.Country;
import com.springcrud1.entity.District;
import com.springcrud1.entity.State;
import com.springcrud1.repository.CityRepository;
import com.springcrud1.repository.Country_Repository;
import com.springcrud1.repository.DistrictRepository;
import com.springcrud1.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private Country_Repository countryRepo;

    @Autowired
    private StateRepository stateRepo;

    @Autowired
    private DistrictRepository districtRepo;

    @Autowired
    private CityRepository cityRepo;

    // ✅ Save Country
    public Country saveCountry(CountryDTO dto) {
        Country country = new Country();
        country.setName(dto.getName());
        return countryRepo.save(country);
    }

    // ✅ Save State
    public StateDTO saveState(StateDTO dto) {

        Country country = countryRepo.findById(dto.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        State state = new State();
        state.setName(dto.getName());
        state.setCountry(country);

        State savedState = stateRepo.save(state);

        // 🔥 Convert to DTO
        StateDTO response = new StateDTO();
        response.setId(savedState.getId());
        response.setName(savedState.getName());
        response.setCountryId(country.getId());
        response.setCountryName(country.getName());

        return response;
    }

    // ✅ Save District
    public DistrictDTO saveDistrict(DistrictDTO dto) {

        State state = stateRepo.findById(dto.getStateId())
                .orElseThrow(() -> new RuntimeException("State not found"));

        District district = new District();
        district.setName(dto.getName());
        district.setState(state);

        District saveDistrict= districtRepo.save(district);
        DistrictDTO response = new DistrictDTO();
        response.setId(saveDistrict.getId());
        response.setName(saveDistrict.getName());
        response.setStateId(state.getId());
        return  response;
    }

    // ✅ Save City
    public CityDTO saveCity(CityDTO dto) {

        District district = districtRepo.findById(dto.getDistrictId())
                .orElseThrow(() -> new RuntimeException("District not found"));

        City city = new City();
        city.setName(dto.getName());
        city.setDistrict(district);
        City save =cityRepo.save(city);
        CityDTO response = new CityDTO();
        response.setName(save.getName());
        return response;
    }
    public List<StateDTO> getStatesByCountry(Long countryId) {

        List<State> states = stateRepo.findByCountryId(countryId);

        return states.stream().map(state -> {
            StateDTO dto = new StateDTO();
            dto.setId(state.getId());
            dto.setName(state.getName());
            dto.setCountryId(state.getCountry().getId());
            dto.setCountryName(state.getCountry().getName());
            return dto;
        }).toList();
    }
    public List<DistrictDTO> getDistrictsByState(Long stateId) {

        List<District> districts = districtRepo.findByStateId(stateId);

        return districts.stream().map(d -> {
            DistrictDTO dto = new DistrictDTO();
            dto.setId(d.getId());
            dto.setName(d.getName());
            dto.setStateId(d.getState().getId());
            return dto;
        }).toList();
    }
    public List<CityDTO> getCitiesByDistrict(Long districtId) {

        List<City> cities = cityRepo.findByDistrictId(districtId);

        return cities.stream().map(c -> {
            CityDTO dto = new CityDTO();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setDistrictId(c.getDistrict().getId());
            return dto;
        }).toList();
    }
    public CountryFullResponseDTO getFullCountry(Long id) {

        Country country = countryRepo.findFullHierarchy(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        CountryFullResponseDTO response = new CountryFullResponseDTO();
        response.setId(country.getId());
        response.setName(country.getName());

        List<StateDTO> stateList = country.getStates().stream().map(state -> {

            StateDTO stateDTO = new StateDTO();
            stateDTO.setId(state.getId());
            stateDTO.setName(state.getName());

            List<DistrictDTO> districtList = state.getDistricts().stream().map(d -> {

                DistrictDTO districtDTO = new DistrictDTO();
                districtDTO.setId(d.getId());
                districtDTO.setName(d.getName());

                List<CityDTO> cityList = d.getCities().stream().map(c -> {
                    CityDTO cityDTO = new CityDTO();
                    cityDTO.setId(c.getId());
                    cityDTO.setName(c.getName());
                    return cityDTO;
                }).toList();

                districtDTO.setCities(cityList);
                return districtDTO;

            }).toList();

            stateDTO.setDistricts(districtList);
            return stateDTO;

        }).toList();

        response.setStates(stateList);

        return response;
    }
}
