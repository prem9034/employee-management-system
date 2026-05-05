package com.springcrud1.service;

import com.springcrud1.model.country;
import com.springcrud1.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public country save(country c) {
        return repository.save(c);
    }

    public List<country> getAll() {
        return repository.findAll();
    }

    public country getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }
}
