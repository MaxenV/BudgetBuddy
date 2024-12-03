package com.example.budget_buddy_android.admin_panel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budget_buddy_android.api.ExpensesRepository
import com.example.budget_buddy_android.api.UserRepository
import com.example.budget_buddy_android.models.Expense
import com.example.budget_buddy_android.models.User

class AdminViewModel : ViewModel() {
    private val userRepository = UserRepository()
    val users = mutableStateListOf<User>()

    fun fetchExpenses() {
        userRepository.fetchAllUsers(viewModelScope) { result ->
            result.onSuccess {
                users.clear()
                users.addAll(it)
            }.onFailure {
                // Handle error
            }
        }
    }
}