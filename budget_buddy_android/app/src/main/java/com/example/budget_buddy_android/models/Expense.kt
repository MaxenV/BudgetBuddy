package com.example.budget_buddy_android.models

import java.math.BigDecimal
import java.util.Date

data class Expense (
    val id: Int,
    val expenseName: String,
    val coast: BigDecimal,
    val category: String,
    val description: String,
    val expenseDateTime: Date
)
