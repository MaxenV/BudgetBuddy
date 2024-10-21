package com.example.budget_buddy;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.budget_buddy.dto.LoginRequest;
import com.example.budget_buddy.dto.RegisterRequest;
import com.example.budget_buddy.service.JwtService;
import com.example.budget_buddy.UserRepository;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public AuthController(JwtService jwtService, UserDetailsService userDetailsService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService; // <-- Wstrzykiwanie UserDetailsService
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && passwordEncoder().matches(loginRequest.getPassword(), user.get().getPassword())) {
            String token = jwtService.generateToken(user.get().getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }



    @PostMapping("/register")
public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
    String encodedPassword = passwordEncoder().encode(registerRequest.getPassword());
    // Zapisz użytkownika z zakodowanym hasłem do bazy danych
    return ResponseEntity.ok("User registered successfully");
}

}