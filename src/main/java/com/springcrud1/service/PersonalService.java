package com.springcrud1.service;

import com.springcrud1.DTO.DepartmentDTO;
import com.springcrud1.DTO.EmployeeDTO;
import com.springcrud1.DTO.EmployeeListDTO;
import com.springcrud1.DTO.EmployeeUpdateDTO;
import com.springcrud1.entity.City;
import com.springcrud1.entity.Department;
import com.springcrud1.entity.EmployeeDetail;
import com.springcrud1.repository.CityRepository;
import com.springcrud1.repository.DepartmentRepository;
import com.springcrud1.repository.EmployeeNewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {
    @Autowired
    private DepartmentRepository departmentRepo;
    @Autowired
    private EmployeeNewRepository employeeRepo;
    @Autowired
    private CityRepository cityRepo;

    public DepartmentDTO saveCountry(DepartmentDTO dto) {
        Department department = new Department();
        department.setVdepartmentName(dto.getDepartmentName());

        Department savedDepartment = departmentRepo.save(department);
        DepartmentDTO response = new DepartmentDTO();
        response.setId(department.getId());
        response.setDepartmentName(department.getVdepartmentName());

        return response;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO dto) {
        Department department = departmentRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        City city = cityRepo.findById(dto.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));

        EmployeeDetail employeeDetail = new EmployeeDetail();
        employeeDetail.setVemployeeName(dto.getVemployeeName());
        employeeDetail.setDepartment(department);
        employeeDetail.setCity(city);
        employeeDetail.setVemployee_mobile(dto.getVemployeeMobile());
        employeeDetail.setVemployeeEmail(dto.getVemployeeEmail());
        employeeDetail.setCempStatus("A");
        employeeDetail.setVempcode(generateEmpCode());

        EmployeeDetail savedEmployee = employeeRepo.save(employeeDetail);

        // Convert to DTO
        EmployeeDTO response = new EmployeeDTO();
        response.setId(savedEmployee.getId());
        response.setVemployeeName(savedEmployee.getVemployeeName());
        response.setVcity(city.getName());
        response.setDepartmentId(department.getId());
        response.setVdepartment(department.getVdepartmentName());
        response.setVemployeeMobile(savedEmployee.getVemployee_mobile());
        response.setVemployeeEmail(savedEmployee.getVemployeeEmail());
        return response;
    }

    public EmployeeDTO updateEmployee(EmployeeUpdateDTO dto) {

        // 1️⃣ Fetch existing employee
        EmployeeDetail emp = employeeRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // 2️⃣ Update basic fields
        emp.setVemployeeName(dto.getEmployeeName());
        emp.setVemployeeEmail(dto.getEmployeeEmail());
        emp.setVemployee_mobile(dto.getEmployeeMobile());
        emp.setCempStatus("A");
        emp.setVempcode(emp.getVempcode());
        // 3️⃣ Update Department (relationship)
        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            emp.setDepartment(dept); // 👈 owner side
        }

        // 4️⃣ Update City
        if (dto.getCityId() != null) {
            City city = cityRepo.findById(dto.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found"));

            emp.setCity(city);
        }

        // 5️⃣ Save
        EmployeeDetail updated = employeeRepo.save(emp);

        // 6️⃣ Convert to DTO
        return mapToDTO(updated);
    }

    private EmployeeDTO mapToDTO(EmployeeDetail emp) {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(emp.getId());
        dto.setVemployeeName(emp.getVemployeeName());
        dto.setVemployeeEmail(emp.getVemployeeEmail());

        if (emp.getDepartment() != null) {
            dto.setVdepartment(emp.getDepartment().getVdepartmentName());
        }

        return dto;
    }

    public String generateEmpCode() {

        Long maxId = employeeRepo.getMaxId();
        Long next = (maxId == null) ? 1 : maxId + 1;

        return String.format("EMP/%02d", next);
    }
    public List<EmployeeListDTO> getAllEmployees() {
        return employeeRepo.getEmployeeFullDetails();
    }
}
