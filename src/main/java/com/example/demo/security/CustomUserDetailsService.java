package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // For the assignment you can later switch this to load from DB.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username here is email
        // TODO: replace with repository.findByEmail(username)
        if (!"test@example.com".equals(username)) {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_USER"));

        // password should already be encoded with BCrypt; "password" is just a placeholder
        return new User(username, "$2a$10$abcdefghijklmnopqrstuv", authorities);
    }
}
