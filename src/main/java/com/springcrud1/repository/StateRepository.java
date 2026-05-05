package com.springcrud1.repository;

import com.springcrud1.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByCountryId(Long countryId);
}