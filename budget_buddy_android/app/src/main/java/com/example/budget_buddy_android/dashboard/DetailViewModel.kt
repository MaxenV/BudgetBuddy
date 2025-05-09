package com.example.budget_buddy_android.dashboard

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budget_buddy_android.api.ExpensesRepository
import com.example.budget_buddy_android.dashboard.helpers.validateExpenseDto
import com.example.budget_buddy_android.dto.ExpenseDto
import com.example.budget_buddy_android.models.Expense
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Calendar

class DetailViewModel : ViewModel() {

    val isEditMode = mutableStateOf(false)
    private var _currentExpense = mutableStateOf<Expense?>(null)

    val costString = mutableStateOf("0.00")
    val costBigDecimal: State<BigDecimal> = derivedStateOf {  _currentExpense.value?.cost ?: BigDecimal(0) }
    val expenseName: State<String> = derivedStateOf { _currentExpense.value?.expenseName ?: "" }
    val category: State<String> = derivedStateOf { _currentExpense.value?.category ?: "" }
    val description: State<String> = derivedStateOf { _currentExpense.value?.description ?: "" }
    var expenseDateTime by mutableStateOf(
        formatExpenseDateTime(_currentExpense.value?.expenseDateTime)
    )

    val calendar: Calendar = Calendar.getInstance().apply {
        _currentExpense.value?.expenseDateTime?.let { time = it }
    }

    fun formatExpenseDateTime(dateTime: Date?): String {
        return dateTime?.let {
            val myFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            myFormat.format(it)
        } ?: ""
    }

    fun fetchExpense(expenseId: Int, expensesRepository: ExpensesRepository, context: Context) {
        viewModelScope.launch {
            expensesRepository.fetchSingleExpense(viewModelScope, expenseId) { result ->
                result.onSuccess { expense ->
                    _currentExpense.value = expense
                    expenseDateTime = formatExpenseDateTime(_currentExpense.value?.expenseDateTime)
                    costString.value = expense.cost.toString()
                }.onFailure { exception ->
                    Toast.makeText(context, "Error while loading date from server", Toast.LENGTH_SHORT)
                }
            }
        }
    }

    fun updateExpense(
        name: String? = _currentExpense.value?.expenseName,
        cost: String? = _currentExpense.value?.cost.toString(),
        category: String? = _currentExpense.value?.category,
        description: String? = _currentExpense.value?.description,
        dateTime: Date? = _currentExpense.value?.expenseDateTime,
    ) {
        if (name != null && cost != null && category != null && description != null && dateTime != null) {
            if (!costFilter(cost)) {
                return
            }
            costString.value = cost

            val toBDecimal = if (cost.isEmpty() || cost == ".") BigDecimal.ZERO else BigDecimal(cost)
            _currentExpense.value = _currentExpense.value?.copy(
                expenseName = name,
                cost = toBDecimal,
                category = category,
                description = description,
                expenseDateTime = dateTime
            )
        }
    }

    fun costFilter(cost:String): Boolean{
        val regex = Regex("^\\d*\\.?\\d{0,2}$")
        return (regex.matches(cost) || cost.isEmpty() || cost == ".")
    }

    fun onCostFocusChange(){
        costString.value = _currentExpense.value?.cost.toString()
    }

    fun saveExpense(expensesRepository: ExpensesRepository, context:Context) {
        if (_currentExpense.value != null) {
            _currentExpense.value?.let { expense ->
                val expenseDto = ExpenseDto(expense)
                viewModelScope.launch {
                    try {
                        validateExpenseDto(expenseDto)
                    } catch (e: IllegalArgumentException) {
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    expensesRepository.updateExpense(
                        viewModelScope,
                        expense.id,
                        expenseDto
                    ) { result ->
                        result.onSuccess { updatedExpense ->
                            toggleEditMode()
                        }.onFailure { exception ->
                            val errMessage = exception.message?.substringAfter("error:") ?: "Some field is empty"
                            Toast.makeText(context, errMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                } 
            }
        }
    }

    fun deleteExpense(expenseId: Int, expensesRepository: ExpensesRepository, onResult: (Result<Unit>) -> Unit) {
        viewModelScope.launch {
            expensesRepository.deleteExpense(viewModelScope, expenseId) { result ->
                result.onSuccess {
                    onResult(Result.success(Unit))
                }.onFailure { exception ->
                    onResult(Result.failure(exception))
                }
            }
        }
    }

    fun toggleEditMode() {
        isEditMode.value = !isEditMode.value
        if (costString.value.isBlank()){
            costString.value = "0.00"
        }
    }
}
