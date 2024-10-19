package com.example.budget_buddy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Jeśli używasz usługi JwtService do generowania tokenów
import org.springframework.beans.factory.annotation.Autowired;

// Klasy do obsługi żądań rejestracji i logowania (musisz je zdefiniować)
import com.example.budget_buddy.dto.RegisterRequest;
import com.example.budget_buddy.dto.LoginRequest;

// Serwis do generowania JWT
import com.example.budget_buddy.service.JwtService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    @Autowired
    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Przykład generowania tokenu JWT
        String token = jwtService.generateToken(loginRequest.getUsername());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        // Tutaj możesz zaimplementować logikę rejestracji
        return ResponseEntity.ok("User registered successfully");
    }
}