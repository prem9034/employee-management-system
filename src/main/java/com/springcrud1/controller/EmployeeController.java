package com.springcrud1.controller;

import com.springcrud1.model.Employee;
import com.springcrud1.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    @PostMapping
    public Employee create(@RequestBody Employee emp) {
        return service.save(emp);
    }
    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }
}
