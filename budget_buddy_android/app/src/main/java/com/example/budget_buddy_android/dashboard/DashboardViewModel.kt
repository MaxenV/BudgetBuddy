package com.example.budget_buddy_android.dashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budget_buddy_android.api.ExpensesRepository
import com.example.budget_buddy_android.models.Expense

class DashboardViewModel : ViewModel() {
    private val expensesRepository = ExpensesRepository()
    val expenses = mutableStateListOf<Expense>()

    fun fetchExpenses() {
        expensesRepository.fetchAllExpenses(viewModelScope) { result ->
            result.onSuccess {
                expenses.clear()
                expenses.addAll(it)
            }.onFailure {
                // Handle error
            }
        }
    }
}