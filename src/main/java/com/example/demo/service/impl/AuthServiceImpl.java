package com.example.demo.service.impl;

import com.example.demo.dto.AuthLoginRequest;
import com.example.demo.dto.AuthRegisterRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthResponse register(AuthRegisterRequest request) {
        // TODO: save user in DB with encoded password
        String token = jwtTokenProvider.generateToken(1L, request.getEmail(), "USER");
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(AuthLoginRequest request) {
        // TODO: load user from DB and check password
        String token = jwtTokenProvider.generateToken(1L, request.getEmail(), "USER");
        return new AuthResponse(token);
    }
}
