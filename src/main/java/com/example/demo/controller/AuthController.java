package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final EmployeeRepository employeeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(EmployeeRepository employeeRepository,
                          JwtTokenProvider jwtTokenProvider) {
        this.employeeRepository = employeeRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Very simple login: find employee by email and issue token
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam(defaultValue = "USER") String role) {
        Optional<Employee> empOpt = employeeRepository.findByEmail(email);
        if (empOpt.isEmpty()) {
            throw new RuntimeException("Employee not found");
        }
        Employee emp = empOpt.get();
        return jwtTokenProvider.generateToken(emp.getId(), emp.getEmail(), role);
    }
}
