package com.springcrud1.service;

import com.springcrud1.model.Employee;
import com.springcrud1.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }
    public Employee save(Employee emp) {
        return repository.save(emp);
    }
    public List<Employee> getAll() {
        return repository.findAll();
    }
}
