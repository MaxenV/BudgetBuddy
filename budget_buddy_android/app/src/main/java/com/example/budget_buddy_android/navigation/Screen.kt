package com.example.budget_buddy_android.navigation

sealed class Screen (
    val route: String
){
    object RegisterScreen : Screen("register_screen")
    object LoginScreen : Screen("login_screen")
    object DashboardScreen : Screen("home_dashboard_screen")
    object DetailScreen : Screen("detail_screen/{expenseId}") {
        fun createRoute(expenseId: Int) = "detail_screen/$expenseId"
    }
    object AddExpenseScreen: Screen("add_expense")
    object AdminDashboardScreen: Screen("admin_dashboard")
    object UserDetailScreen : Screen("user_detail_screen/{userId}") {
        fun createRoute(userId: Int) = "user_detail_screen/$userId"
    }
}