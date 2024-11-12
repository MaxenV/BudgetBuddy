package com.example.budget_buddy_android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.api.UserRepository
import com.example.budget_buddy_android.login_register.LoginView
import com.example.budget_buddy_android.login_register.RegisterView
import com.example.budget_buddy_android.user_dashboard.HomeDashboard

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    userRepository: UserRepository = UserRepository()
) {

    NavHost(
        navController = navController, startDestination = Screen.LoginScreen.route
    ) {
        composable(Screen.RegisterScreen.route) {
            RegisterView(navController = navController, userRepository = userRepository)
        }
        composable(Screen.LoginScreen.route) {
            LoginView(navController = navController, userRepository = userRepository)
        }
        composable(Screen.HomeDashboardScreen.route) {
            HomeDashboard()
        }
    }
}