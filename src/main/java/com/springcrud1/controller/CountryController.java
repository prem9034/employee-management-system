package com.springcrud1.controller;

import com.springcrud1.model.country;
import com.springcrud1.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @PostMapping
    public country create(@RequestBody country c) {
        return service.save(c);
    }

    @GetMapping
    public List<country> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public country getById(@PathVariable Long id) {
        return service.getById(id);
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
