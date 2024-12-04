package com.example.budget_buddy_android.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.api.UserRepository
import com.example.budget_buddy_android.models.Expense
import com.example.budget_buddy_android.ui.components.TopBar
import java.math.BigDecimal
import java.util.Date

@Composable
fun UserDetailView(
    userId: Int, navController: NavController, userRepository: UserRepository
) {
    val viewModel: UserDetailViewModel = viewModel()

    LaunchedEffect(userId) {
        viewModel.fetchExpense(userId, userRepository)
    }
    Scaffold(
        topBar = { TopBar(navController, "User details") }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(modifier = Modifier.padding(8.dp)) {
                Text("User ID: ")
                Text(viewModel.userData.value?.id?.toString() ?: "N/A")
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Full Name: ")
                Text(viewModel.userData.value?.fullName ?: "N/A")
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Email: ")
                Text(viewModel.userData.value?.email ?: "N/A")
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Admin: ")
                Text(if (viewModel.userData.value?.isAdmin == true) "Yes" else "No")
            }
        }
    }
}

@Preview
@Composable
fun UserDetailViewPreview() {
    val expense = Expense(1, "Groceries", BigDecimal("50.00"), "Food", "Weekly groceries", Date())
    val navController = rememberNavController()
    val userRepository: UserRepository = UserRepository()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            UserDetailView(expense.id, navController, userRepository)
        }
    }
}