package com.evaitcs.securebank12july.model;


import com.evaitcs.securebank12july.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // اسم المستخدم
    // Username used for login
    @Column(nullable = false, unique = true)
    private String username;

    // كلمة المرور المشفرة
    // Encrypted password
    @Column(nullable = false)
    private String password;

    // صلاحية المستخدم
    // User role
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // هل المستخدم فعال؟
    // Is the user enabled?
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}

//CUSTOMER → ROLE_CUSTOMER
//TELLER   → ROLE_TELLER
//ADMIN    → ROLE_ADMIN