package com.example.budget_buddy_android.dashboard.helpers

import com.example.budget_buddy_android.dto.ExpenseDto

fun validateExpenseDto(expense: ExpenseDto) {
    if (expense.expenseName.isBlank()) {
        throw IllegalArgumentException("Expense name cannot be empty")
    }
    if (expense.category.isBlank()){
        throw IllegalArgumentException("Expense category cannot be empty")
    }
}