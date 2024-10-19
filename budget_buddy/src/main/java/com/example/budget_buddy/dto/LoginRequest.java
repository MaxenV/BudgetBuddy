package com.example.budget_buddy.dto;

public class LoginRequest {
    private String username;
    private String password;

    // Konstruktor bezargumentowy
    public LoginRequest() {
    }

    // Konstruktor z argumentami (opcjonalnie)
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Gettery i settery
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}