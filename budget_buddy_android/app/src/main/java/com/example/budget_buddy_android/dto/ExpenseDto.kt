package com.example.budget_buddy_android.dto

import com.example.budget_buddy_android.models.Expense
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ExpenseDto(
    val expenseName: String,
    val cost: BigDecimal,
    val category: String,
    val description: String,
    val expenseDateTime: String
) {
    constructor(expense: Expense) : this(
        expenseName = expense.expenseName,
        cost = expense.cost,
        category = expense.category,
        description = expense.description,
        expenseDateTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault()).format(expense.expenseDateTime)
    )
    constructor():this(
        expenseName = "",
        cost = BigDecimal(0),
        category = "",
        description = "",
       expenseDateTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault()).format(
           Date()
       )
    )
}