package com.example.budget_buddy_android.dashboard

import android.content.Context
import android.widget.Toast
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
    val costString = mutableStateOf(_currentExpense.value.cost.toString())

    val expenseName: State<String> = derivedStateOf { _currentExpense.value.expenseName }
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
        cost: String? = _currentExpense.value.cost.toString(),
        category: String? = _currentExpense.value.category,
        description: String? = _currentExpense.value.description,
        dateTime: String? = _currentExpense.value.expenseDateTime
    ) {
        if (name != null && cost != null && category != null && description != null && dateTime != null) {
                    val toBDecimal = if (cost.isEmpty() || cost == ".") BigDecimal.ZERO else BigDecimal(cost)
                    _currentExpense.value = _currentExpense.value.copy(
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
        costString.value = _currentExpense.value.cost.toString()
    }

    fun addExpense(expensesRepository: ExpensesRepository, navController: NavController, context: Context) {
        viewModelScope.launch {
            expensesRepository.addExpense(viewModelScope, _currentExpense.value) { result ->
                result.onSuccess { addedExpense ->
                    navController.navigate(Screen.DashboardScreen.route)
                }.onFailure { exception ->
                    val errMessage = exception.message?.substringAfter("error:") ?: "Some field is empty"
                    Toast.makeText(context, errMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun updateExpenseDateTime(date: Date) {
        val formattedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault()).format(date)
        updateExpense(dateTime = formattedDate)
    }
}