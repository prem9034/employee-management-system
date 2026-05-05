package com.springcrud1.controller;

import com.springcrud1.DTO.DepartmentDTO;
import com.springcrud1.DTO.EmployeeDTO;
import com.springcrud1.DTO.EmployeeListDTO;
import com.springcrud1.DTO.EmployeeUpdateDTO;
import com.springcrud1.entity.Department;
import com.springcrud1.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal")
public class PersonalController {
    @Autowired
    private PersonalService service;

    @PostMapping("/Department")
    public DepartmentDTO saveDepartment(@RequestBody DepartmentDTO dto) {
        return service.saveCountry(dto);
    }

    @PostMapping("/Employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO dto) {
        return service.saveEmployee(dto);
    }

    @PutMapping("/UpdateEmployee")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeUpdateDTO dto) {
        return service.updateEmployee(dto);
    }
    @GetMapping("/employees")
    public List<EmployeeListDTO> getAll() {
        return service.getAllEmployees();
    }
}
