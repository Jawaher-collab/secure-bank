package com.evaitcs.securebank12july.service;

import com.evaitcs.securebank12july.model.User;
import com.evaitcs.securebank12july.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // إنشاء مستخدم
    // Create a user
    public User createUser(User user) {


        user.setPassword(passwordEncoder.encode(user.getPassword())
        );
        return userRepository.save(user);
    }

    // جلب جميع المستخدمين
    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // جلب مستخدم بواسطة ID
    // Get a user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow();
    }

    // حذف مستخدم
    // Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}