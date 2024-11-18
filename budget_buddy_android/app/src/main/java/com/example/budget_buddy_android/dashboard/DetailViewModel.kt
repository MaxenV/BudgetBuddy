package com.example.budget_buddy_android.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budget_buddy_android.api.ExpensesRepository
import com.example.budget_buddy_android.models.Expense
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel : ViewModel() {

    val isEditMode = mutableStateOf(false)
    private var _currentExpense = mutableStateOf<Expense?>(null)

    val expenseName: State<String> = derivedStateOf { _currentExpense.value?.expenseName ?: "" }
    val coast: State<String> = derivedStateOf { _currentExpense.value?.coast?.toString() ?: "" }
    val category: State<String> = derivedStateOf { _currentExpense.value?.category ?: "" }
    val description: State<String> = derivedStateOf { _currentExpense.value?.description ?: "" }
    val expenseDateTime: State<String> =
        derivedStateOf { formatExpenseDateTime(_currentExpense.value?.expenseDateTime) }

    private fun formatExpenseDateTime(dateTime: Date?): String {

        return dateTime?.let {
            val myFormat = SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault()
            )
            myFormat.format(it)
        } ?: ""
    }

    fun fetchExpense(expenseId: Int, expensesRepository: ExpensesRepository) {
        viewModelScope.launch {
            expensesRepository.fetchSingleExpense(viewModelScope, expenseId) { result ->
                result.onSuccess { expense ->
                    _currentExpense.value = expense

                }.onFailure { exception ->
                    // Handle the error appropriately
                }
            }
        }
    }

    fun changeExpense(
        name: String? = _currentExpense.value?.expenseName,
        coast: BigDecimal? = _currentExpense.value?.coast,
        category: String? = _currentExpense.value?.category,
        description: String? = _currentExpense.value?.description,
        expenseDateTime: Date? = _currentExpense.value?.expenseDateTime,
    ) {
        if (name != null && coast != null && category != null && description != null && expenseDateTime != null) {
            _currentExpense.value = _currentExpense.value?.copy(
                expenseName = name,
                coast = coast,
                category = category,
                description = description,
                expenseDateTime = expenseDateTime
            )
        }

    }
    fun toggleEditMode() {
        isEditMode.value = !isEditMode.value
    }
}
