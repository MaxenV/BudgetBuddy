package com.example.budget_buddy.service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.budget_buddy.User;
import com.example.budget_buddy.UserRepository;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Znajdź użytkownika po emailu
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            new ArrayList<>()); // Lista ról, jeśli potrzebna
    }
}
