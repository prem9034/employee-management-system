package com.springcrud1.repository;

import com.springcrud1.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Country_Repository extends JpaRepository<Country, Long> {
    @Query("SELECT DISTINCT c FROM Country c LEFT JOIN FETCH c.states s LEFT JOIN FETCH s.districts d LEFT JOIN FETCH d.cities WHERE c.id = :id ")
    Optional<Country> findFullHierarchy(@Param("id") Long id);
}
