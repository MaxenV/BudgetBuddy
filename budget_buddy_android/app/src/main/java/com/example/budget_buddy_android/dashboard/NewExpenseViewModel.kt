package com.example.budget_buddy_android.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.budget_buddy_android.api.ExpensesRepository
import com.example.budget_buddy_android.dto.ExpenseDto
import com.example.budget_buddy_android.navigation.Screen
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class NewExpenseViewModel : ViewModel() {
    private var _currentExpense = mutableStateOf(ExpenseDto())

    val expenseName: State<String> = derivedStateOf { _currentExpense.value.expenseName }
    val cost: State<String> = derivedStateOf { _currentExpense.value.cost.toString() }
    val category: State<String> = derivedStateOf { _currentExpense.value.category }
    val description: State<String> = derivedStateOf { _currentExpense.value.description }
    val expenseDateTime: State<String> = derivedStateOf { _currentExpense.value.expenseDateTime }

    val calendar: Calendar = Calendar.getInstance().apply {
        _currentExpense.value.expenseDateTime.let {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault()).parse(it)
            time = date
        }
    }

    fun updateExpense(
        name: String? = _currentExpense.value.expenseName,
        cost: BigDecimal? = _currentExpense.value.cost,
        category: String? = _currentExpense.value.category,
        description: String? = _currentExpense.value.description,
        dateTime: String? = _currentExpense.value.expenseDateTime
    ) {
        if (name != null && cost != null && category != null && description != null && dateTime != null) {
            _currentExpense.value = _currentExpense.value.copy(
                expenseName = name,
                cost = cost,
                category = category,
                description = description,
                expenseDateTime = dateTime
            )
        }
    }

    fun addExpense(expensesRepository: ExpensesRepository, navController: NavController) {
        viewModelScope.launch {
            expensesRepository.addExpense(viewModelScope, _currentExpense.value) { result ->
                result.onSuccess { addedExpense ->
                    navController.navigate(Screen.DashboardScreen.route)
                }.onFailure { exception ->
                    // Handle error
                }
            }
        }
    }

    fun updateExpenseDateTime(date: Date) {
        val formattedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault()).format(date)
        updateExpense(dateTime = formattedDate)
    }
}