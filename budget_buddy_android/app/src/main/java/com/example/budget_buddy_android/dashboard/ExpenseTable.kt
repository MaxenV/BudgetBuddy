package com.example.budget_buddy_android.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.budget_buddy_android.models.Expense

@Composable
fun ExpenseTable(expenses: List<Expense>) {
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
            )
            Text(
                "Coast",
                modifier = Modifier
                    .width(100.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Text("Category", modifier = Modifier
                .width(100.dp)
                .wrapContentWidth(Alignment.End))
        }
        LazyColumn(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            items(expenses) { expense ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(8.dp, 15.dp)
                        .fillMaxWidth()
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