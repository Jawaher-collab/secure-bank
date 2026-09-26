package com.evaitcs.securebank12july.service;

import com.evaitcs.securebank12july.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankUserDetailsService implements UserDetailsService {

    // Access user data from the database
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        // Find the user by username or throw an exception if not found
        var user = userRepository.findByUsername(username)
                .orElseThrow();
        // Convert our User to Spring Security UserDetails
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}