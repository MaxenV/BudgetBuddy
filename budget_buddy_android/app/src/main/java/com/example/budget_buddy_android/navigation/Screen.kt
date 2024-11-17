package com.example.budget_buddy_android.navigation

sealed class Screen (
    val route: String
){
    object RegisterScreen : Screen("register_screen")
    object LoginScreen : Screen("login_screen")
    object DashboardScreen : Screen("home_dashboard_screen")
    object DetailScreen : Screen("detail_screen")
}