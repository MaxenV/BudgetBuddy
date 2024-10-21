package com.example.budget_buddy.dto;

public class LoginRequest {
    private String email;
    private String password;

    // Konstruktor bezargumentowy
    public LoginRequest() {
    }

    // Konstruktor z argumentami (opcjonalnie)
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Gettery i settery
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
