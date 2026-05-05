package com.springcrud1.repository;

import com.springcrud1.model.country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<country, Long> {
}
