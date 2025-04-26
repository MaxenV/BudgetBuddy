package com.example.budget_buddy_android.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.api.ExpensesRepository
import com.example.budget_buddy_android.models.Expense
import com.example.budget_buddy_android.ui.components.TopBar
import com.example.budget_buddy_android.navigation.Screen
import com.example.budget_buddy_android.ui.components.TopBarConf
import java.math.BigDecimal
import java.util.Date

@Composable
fun DetailView(
    expenseId: Int, navController: NavController, expenseRepository: ExpensesRepository
) {
    val viewModel: DetailViewModel = viewModel()
    val context = LocalContext.current

    val viewModelScope = viewModel.viewModelScope

    LaunchedEffect(expenseId) {
        viewModel.fetchExpense(expenseId, expenseRepository)
    }
    Scaffold(
        topBar = { TopBar(navController, "Details", viewModelScope = viewModelScope) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Expense Name: ")
                if (viewModel.isEditMode.value) {
                    TextField(value = viewModel.expenseName.value,
                        onValueChange = { name: String -> viewModel.updateExpense(name = name) })
                } else {
                    Text(viewModel.expenseName.value)
                }
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Cost: ")
                if (viewModel.isEditMode.value) {
                    TextField(value = viewModel.costString.value,
                        onValueChange = { cost: String ->
                            viewModel.updateExpense(cost = cost)
                        }
                    )
                } else {
                    Text(viewModel.costBigDecimal.value.toString())
                }
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Category: ")
                if (viewModel.isEditMode.value) {
                    TextField(value = viewModel.category.value,
                        onValueChange = { category: String -> viewModel.updateExpense(category = category) })
                } else {
                    Text(viewModel.category.value)
                }
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Description: ")
                if (viewModel.isEditMode.value) {
                    TextField(value = viewModel.description.value,
                        onValueChange = { description: String -> viewModel.updateExpense(description = description) })
                } else {
                    Text(viewModel.description.value)
                }
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Expense Date: ")
                if (viewModel.isEditMode.value) {
                    DateTimePicker(viewModel = viewModel)
                } else {
                    Text(viewModel.expenseDateTime)
                }
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Button(onClick = {
                    if (viewModel.isEditMode.value) {
                        viewModel.saveExpense(expenseRepository, context)
                    }
                    else{
                        viewModel.toggleEditMode()
                    }
                }) {
                    Text(if (viewModel.isEditMode.value) "Save" else "Edit")
                }
                Button(onClick = {
                    viewModel.deleteExpense(expenseId, expenseRepository) { result ->
                        result.onSuccess {
                            navController.navigate(Screen.DashboardScreen.route)
                        }.onFailure { exception ->
                            // Handle Exception
                        }
                    }
                }) {
                    Text("Delete")
                }
            }
        }
    }

}

@Preview
@Composable
fun DetailViewPreview() {
    val expense = Expense(1, "Groceries", BigDecimal("50.00"), "Food", "Weekly groceries", Date())
    val navController = rememberNavController()
    val expenseRepository: ExpensesRepository = ExpensesRepository()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            DetailView(expense.id, navController, expenseRepository)
        }
    }
}