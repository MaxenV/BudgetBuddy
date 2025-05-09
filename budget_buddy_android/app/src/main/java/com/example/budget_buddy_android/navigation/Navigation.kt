package com.example.budget_buddy_android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.admin_panel.AdminDashboardView
import com.example.budget_buddy_android.api.ExpensesRepository
import com.example.budget_buddy_android.api.UserRepository
import com.example.budget_buddy_android.login_register.LoginView
import com.example.budget_buddy_android.login_register.RegisterView
import com.example.budget_buddy_android.dashboard.DashboardView
import com.example.budget_buddy_android.dashboard.DetailView
import com.example.budget_buddy_android.dashboard.NewExpenseView
import com.example.budget_buddy_android.dashboard.UserDetailView
import com.example.budget_buddy_android.ui.components.TopBar

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    userRepository: UserRepository = UserRepository(),
    expenseRepository: ExpensesRepository = ExpensesRepository()
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
        composable(Screen.DashboardScreen.route) {
            DashboardView(navController = navController, userRepository = userRepository)
        }
        composable(Screen.DetailScreen.route) { backStackEntry ->
            val expenseId = backStackEntry.arguments?.getString("expenseId")?.toInt() ?: 0
            DetailView(
                expenseId = expenseId,
                navController = navController,
                expenseRepository = expenseRepository
            )
        }
        composable(Screen.AddExpenseScreen.route) {
            NewExpenseView(navController = navController, expenseRepository = expenseRepository)
        }
        composable(Screen.AdminDashboardScreen.route) {
            AdminDashboardView(navController,userRepository)
        }
        composable(Screen.UserDetailScreen.route) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 0
            UserDetailView(
                userId = userId,
                navController = navController,
                userRepository = userRepository
            )
        }
    }
}