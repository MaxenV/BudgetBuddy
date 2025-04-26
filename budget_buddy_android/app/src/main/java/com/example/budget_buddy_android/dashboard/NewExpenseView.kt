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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.api.ExpensesRepository
import com.example.budget_buddy_android.models.Expense
import com.example.budget_buddy_android.ui.components.TopBar
import com.example.budget_buddy_android.ui.components.TopBarConf
import java.math.BigDecimal
import java.util.Date

@Composable
fun NewExpenseView(navController: NavController, expenseRepository: ExpensesRepository) {
    val viewModel: NewExpenseViewModel = viewModel()
    val viewModelScope = viewModel.viewModelScope

    Scaffold(
        topBar = {
            TopBar(
                navController, "New Expense", TopBarConf(
                    addExpense = false
                ), viewModelScope
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Expense Name: ")
                TextField(value = viewModel.expenseName.value,
                    onValueChange = { name: String -> viewModel.updateExpense(name = name) })
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Cost: ")
                TextField(value = viewModel.costString.value,
                    onValueChange = { cost: String ->
                        if (viewModel.costFilter(cost)) {
                            viewModel.costString.value = cost
                            viewModel.updateExpense(cost = cost)
                        }
                    },
                    modifier = Modifier.onFocusChanged { viewModel.onCostFocusChange() })
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Category: ")
                TextField(value = viewModel.category.value,
                    onValueChange = { category: String -> viewModel.updateExpense(category = category) })
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Description: ")
                TextField(value = viewModel.description.value,
                    onValueChange = { description: String -> viewModel.updateExpense(description = description) })
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Text("Expense Date: ")
                NewExpenseDateTimePicker(viewModel = viewModel)
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Button(onClick = {
                    viewModel.addExpense(expenseRepository, navController)
                }) {
                    Text("Save")
                }
            }
        }
    }
}

@Preview
@Composable
fun NewExpenseViewPreview() {
    val expense = Expense(1, "Groceries", BigDecimal("50.00"), "Food", "Weekly groceries", Date())
    val navController = rememberNavController()
    val expenseRepository: ExpensesRepository = ExpensesRepository()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NewExpenseView(navController, expenseRepository)
        }
    }
}