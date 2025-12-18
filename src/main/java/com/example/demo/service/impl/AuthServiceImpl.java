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
        // TODO: save user to DB with encoded password
        // String encoded = passwordEncoder.encode(request.getPassword());
        // userRepo.save(...);

        String token = jwtTokenProvider.generateToken(request.getEmail());
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(AuthLoginRequest request) {
        // TODO: load user from DB, verify password with passwordEncoder.matches(...)
        String token = jwtTokenProvider.generateToken(request.getEmail());
        return new AuthResponse(token);
    }
}
