package com.evaitcs.securebank12july.controller;

import com.evaitcs.securebank12july.LoginRequest;
import com.evaitcs.securebank12july.RegisterRequest;
import com.evaitcs.securebank12july.model.Customer;
import com.evaitcs.securebank12july.model.User;
import com.evaitcs.securebank12july.model.enums.Role;
import com.evaitcs.securebank12july.service.CustomerService;
import com.evaitcs.securebank12july.service.JwtService;
import com.evaitcs.securebank12july.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

private final JwtService jwtService;
private final AuthenticationManager authenticationManager;
private final UserService userService;
private final CustomerService customerService;

// Create a user
@PostMapping("/register")
public User createUser(@RequestBody RegisterRequest request) {

    if (request.getRole() != Role.CUSTOMER) {
        throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "You are not authorized to register with this role"
        );
    }

    User user = new User();

    user.setUsername(request.getUsername());
    user.setPassword(request.getPassword());
    user.setRole(request.getRole());

    User savedUser = userService.createUser(user);


    Customer customer = new Customer();
    customer.setFirstName(request.getFirstName());
    customer.setLastName(request.getLastName());
    customer.setEmail(request.getEmail());
    customer.setPhone(request.getPhone());
    customer.setDateOfBirth(request.getDateOfBirth());
    customer.setAddress(request.getAddress());
    customer.setUser(savedUser);
    customerService.createCustomer(customer);

    return savedUser;


}



    @GetMapping("/me")
    public Map<String, String> getCurrentUser(Authentication authentication) {

        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        return Map.of("username", username,
        "role", user.getRole().name()) ;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String token = jwtService.generateToken(request.getUsername());
        return token;
    }

}
