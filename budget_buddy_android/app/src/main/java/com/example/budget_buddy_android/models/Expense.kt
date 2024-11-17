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

fun getTestExpenses(): List<Expense> {
    return listOf(
        Expense(1, "Groceries", BigDecimal("50.00"), "Food", "Weekly groceries", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electrdddddddddddddddddddddddddddddddddddicity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(2, "Electricity Bill", BigDecimal("75.00"), "Utilities", "Monthly bill", Date()),
        Expense(3, "Internet", BigDecimal("30.00"), "Utilities", "Monthly bill", Date())
    )
}