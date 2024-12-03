package com.example.budget_buddy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.budget_buddy.model.User;
import com.example.budget_buddy.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    @PostConstruct
    public void init() {
        String adminEmail = "admin@example.com";
        String adminPassword = "admin";

        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setFullName("Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setIsAdmin(true);
            userRepository.save(admin);
        }
    }
}