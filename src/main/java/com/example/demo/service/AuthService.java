package com.example.demo.service;

public interface AuthService {

    /**
     * Simple login: find employee by email and generate JWT with given role.
     */
    String login(String email, String role);
}
