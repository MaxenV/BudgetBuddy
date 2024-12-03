package com.example.budget_buddy_android.models

data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token:String,
    val expiresIn: Int
)

