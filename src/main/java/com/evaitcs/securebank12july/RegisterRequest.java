package com.evaitcs.securebank12july;

import com.evaitcs.securebank12july.model.enums.Role;
import lombok.Data;

import java.time.LocalDate;
@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String address;
}
