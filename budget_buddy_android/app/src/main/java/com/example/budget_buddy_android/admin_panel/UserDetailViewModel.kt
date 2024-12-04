package com.example.budget_buddy_android.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budget_buddy_android.api.UserRepository
import com.example.budget_buddy_android.models.User
import kotlinx.coroutines.launch

class UserDetailViewModel : ViewModel() {

    private var _userData = mutableStateOf<User?>(null)
    var userData: State<User?> = _userData

    fun fetchExpense(expenseId: Int, expensesRepository: UserRepository) {
        viewModelScope.launch {
            expensesRepository.fetchUser(viewModelScope, expenseId) { result ->
                result.onSuccess { userData ->
                    _userData.value = userData
                }.onFailure { exception ->
                    // Handle the error appropriately
                }
            }
        }
    }
}