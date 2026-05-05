package com.springcrud1.repository;

import com.springcrud1.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByDistrictId(Long districtId);
}