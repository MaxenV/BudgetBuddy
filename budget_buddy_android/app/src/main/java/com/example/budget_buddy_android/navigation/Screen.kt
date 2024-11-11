package com.example.budget_buddy_android.navigation

sealed class Screen (
    val route: String
){
    object RegisterScreen : Screen("register_screen")
}