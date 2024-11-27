package com.example.budget_buddy_android.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.api.UserRepository
import androidx.compose.material3.Surface
import com.example.budget_buddy_android.navigation.Screen
import com.example.budget_buddy_android.ui.components.TopBar

@Composable
fun DashboardView(
    navController: NavController, userRepository: UserRepository
) {
    val viewModel: DashboardViewModel = viewModel()

    fun onLogout(){

    }
    fun onAddExpense(){
    }
    
    LaunchedEffect(Unit) {
        viewModel.fetchExpenses()
    }
    Scaffold(
        topBar = { TopBar(navController,"My dashboard", { onLogout() }, { onAddExpense() }) }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ExpenseTable(expenses = viewModel.expenses, navController = navController)
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
            DashboardView(navController, UserRepository())
        }
    }
}