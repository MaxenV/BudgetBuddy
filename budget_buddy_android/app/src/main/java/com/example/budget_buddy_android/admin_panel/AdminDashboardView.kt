package com.example.budget_buddy_android.admin_panel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.api.UserRepository
import com.example.budget_buddy_android.dashboard.DashboardViewModel
import com.example.budget_buddy_android.dashboard.ExpenseTable
import com.example.budget_buddy_android.dashboard.UsersTable
import com.example.budget_buddy_android.ui.components.TopBar

@Composable
fun AdminDashboardView(
    navController: NavController, userRepository: UserRepository
) {
    val viewModel: DashboardViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchExpenses()
    }
    Scaffold(
        topBar = { TopBar(navController,"My dashboard") }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                UsersTable(users = viewModel.expenses, navController = navController)
            }
        }
    }
}

@Preview
@Composable
fun DashboardViewPreview() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AdminDashboardView(navController, UserRepository())
        }
    }
}