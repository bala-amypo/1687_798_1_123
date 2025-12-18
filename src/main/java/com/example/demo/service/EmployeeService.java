package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee){
        employeeRepository.findByEmail(employee.getEmail())
                .ifPresent(e -> {throw new IllegalArgumentException("Email already exists");});
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee){
        Employee existing = getEmployeeById(id);
        existing.setFullName(employee.getFullName());
        existing.setDepartment(employee.getDepartment());
        existing.setJobTitle(employee.getJobTitle());
        return employeeRepository.save(existing);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void deactivateEmployee(Long id){
        Employee employee = getEmployeeById(id);
        employee.setActive(false);
        employeeRepository.save(employee);
    }
}
