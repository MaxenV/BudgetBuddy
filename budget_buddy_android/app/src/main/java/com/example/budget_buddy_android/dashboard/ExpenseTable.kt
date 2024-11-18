package com.example.budget_buddy_android.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.models.Expense
import com.example.budget_buddy_android.navigation.Screen
import java.math.BigDecimal
import java.util.Date

@Composable
fun ExpenseTable(expenses: List<Expense>, navController:NavController) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Expense Name",
                modifier = Modifier
                    .width(150.dp)
                    .wrapContentWidth(Alignment.Start)
                    .padding(start = 5.dp)
            )
            Text(
                "Coast",
                modifier = Modifier
                    .width(100.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Text("Category", modifier = Modifier
                .width(100.dp)
                .wrapContentWidth(Alignment.End)
                .padding(end = 5.dp)
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            items(expenses) { expense ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(5.dp, 10.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.DetailScreen.createRoute(expense.id))
                        }
                ) {
                    Text(
                        expense.expenseName,
                        modifier = Modifier
                            .width(150.dp)
                            .wrapContentWidth(Alignment.Start)
                    )
                    Text(
                        expense.coast.toString(),
                        modifier = Modifier
                            .width(100.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                    Text(
                        expense.category,
                        modifier = Modifier
                            .width(100.dp)
                            .wrapContentWidth(Alignment.End)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ExpenseTablePreview(){
    val expenseList = listOf(
        Expense(1, "Groceries", BigDecimal("50.00"), "Food", "Weekly groceries", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
    )
    val navController: NavController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            ExpenseTable(expenseList,navController)
        }
    }
}