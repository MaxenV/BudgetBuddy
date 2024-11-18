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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.api.ExpensesRepository
import com.example.budget_buddy_android.models.Expense
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DetailView(
    expenseId: Int, navController: NavController, expenseRepository: ExpensesRepository
) {
    val viewModel: DetailViewModel = viewModel()

    LaunchedEffect(expenseId) {
        viewModel.fetchExpense(expenseId, expenseRepository)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text("Expense Name: ")
            if (viewModel.isEditMode.value) {
                TextField(value = viewModel.expenseName.value,
                    onValueChange = { name: String -> viewModel.changeExpense(name = name) })
            } else {
                Text(viewModel.expenseName.value)
            }
        }
        Row(modifier = Modifier.padding(8.dp)) {
            Text("Coast: ")
            if (viewModel.isEditMode.value) {
                TextField(value = viewModel.coast.value, onValueChange = { coast: String ->
                    val toBDecimal = BigDecimal(coast) ?: null
                    viewModel.changeExpense(coast = toBDecimal)
                })
            } else {
                Text(viewModel.coast.value)
            }
        }
        Row(modifier = Modifier.padding(8.dp)) {
            Text("Category: ")
            if (viewModel.isEditMode.value) {
                TextField(value = viewModel.category.value,
                    onValueChange = { category: String -> viewModel.changeExpense(category = category) })
            } else {
                Text(viewModel.category.value)
            }
        }
        Row(modifier = Modifier.padding(8.dp)) {
            Text("Description: ")
            if (viewModel.isEditMode.value) {
                TextField(value = viewModel.description.value,
                    onValueChange = { description: String -> viewModel.changeExpense(description = description) })
            } else {
                Text(viewModel.description.value)
            }
        }
        Row(modifier = Modifier.padding(8.dp)) {
            Text("Expense Date: ")
            if (viewModel.isEditMode.value) {
                TextField(value = viewModel.expenseDateTime.value,
                    onValueChange = { expenseDateTime: String ->
                        val dateFormat =
                            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                        try {
                            val date = dateFormat.parse(expenseDateTime)
                            if (date != null) {
                                viewModel.changeExpense(expenseDateTime = date)
                            }
                        } catch (e: Exception) {
                            // Handle the error appropriately, e.g., show a message to the user
                        }
                    })
            } else {
                Text(viewModel.expenseDateTime.value)
            }
        }
        Row(modifier = Modifier.padding(8.dp)) {
            Button(onClick = { viewModel.toggleEditMode() }) {
                Text(if (viewModel.isEditMode.value) "Save" else "Edit")
            }
            Button(onClick = { /* Add delete functionality here */ }) {
                Text("Delete")
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