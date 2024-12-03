package com.example.budget_buddy_android.api

import com.example.budget_buddy_android.api.ApiClient.apiService
import com.example.budget_buddy_android.dto.ExpenseDto
import com.example.budget_buddy_android.models.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException

class ExpensesRepository {
    fun fetchAllExpenses(
        viewModelScope: CoroutineScope, onResult: (Result<List<Expense>>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val token = ApiClient.getToken()
                if (token != null) {
                    val response = apiService.allExpenses(token)
                    if (response.isSuccessful) {
                        onResult(Result.success(response.body() ?: emptyList()))
                    } else {
                        val errorBody = response.errorBody()?.string()
                        onResult(Result.failure(Exception("Failed with code ${response.code()}, error: $errorBody")))
                    }
                } else {
                    onResult(Result.failure(Exception("Token is null")))
                }
            } catch (e: HttpException) {
                onResult(Result.failure(Exception("HTTP error: ${e.message}")))
            } catch (e: ConnectException) {
                onResult(Result.failure(Exception("Cannot connect to server")))
            } catch (e: Exception) {
                onResult(Result.failure(Exception("Error fetching expenses: $e")))
            }
        }
    }

    fun fetchSingleExpense(
        viewModelScope: CoroutineScope, expenseId: Int, onResult: (Result<Expense>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val token = ApiClient.getToken()
                if (token != null) {
                    val response = apiService.singleExpense(token, expenseId)
                    if (response.isSuccessful) {
                        onResult(Result.success(response.body()!!))
                    } else {
                        val errorBody = response.errorBody()?.string()
                        onResult(Result.failure(Exception("Failed with code ${response.code()}, error: $errorBody")))
                    }
                } else {
                    onResult(Result.failure(Exception("Token is null")))
                }
            } catch (e: HttpException) {
                onResult(Result.failure(Exception("HTTP error: ${e.message}")))
            } catch (e: ConnectException) {
                onResult(Result.failure(Exception("Cannot connect to server")))
            } catch (e: Exception) {
                onResult(Result.failure(Exception("Error fetching expense: $e")))
            }
        }

    }

    fun updateExpense(
        viewModelScope: CoroutineScope,
        expenseId: Int,
        expenseDto: ExpenseDto,
        onResult: (Result<ExpenseDto>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val token = ApiClient.getToken()
                if (token != null) {
                    val response = apiService.updateExpense(token, expenseId, expenseDto)
                    if (response.isSuccessful) {
                        onResult(Result.success(response.body()!!))
                    } else {
                        val errorBody = response.errorBody()?.string()
                        onResult(Result.failure(Exception("Failed with code ${response.code()}, error: $errorBody")))
                    }
                } else {
                    onResult(Result.failure(Exception("Token is null")))
                }
            } catch (e: HttpException) {
                onResult(Result.failure(Exception("HTTP error: ${e.message}")))
            } catch (e: ConnectException) {
                onResult(Result.failure(Exception("Cannot connect to server")))
            } catch (e: Exception) {
                onResult(Result.failure(Exception("Error fetching expense: $e")))
            }
        }
    }

    fun addExpense(
        viewModelScope: CoroutineScope,expenseDto: ExpenseDto, onResult: (Result<ExpenseDto>) -> Unit) {
        viewModelScope.launch {
            try {
                val token = ApiClient.getToken()
                if (token != null) {
                    val response = apiService.addExpense(token, expenseDto)
                    if (response.isSuccessful) {
                        onResult(Result.success(response.body()!!))
                    } else {
                        val errorBody = response.errorBody()?.string()
                        onResult(Result.failure(Exception("Failed with code ${response.code()}, error: $errorBody")))
                    }
                } else {
                    onResult(Result.failure(Exception("Token is null")))
                }
            } catch (e: HttpException) {
                onResult(Result.failure(Exception("HTTP error: ${e.message}")))
            } catch (e: ConnectException) {
                onResult(Result.failure(Exception("Cannot connect to server")))
            } catch (e: Exception) {
                onResult(Result.failure(Exception("Error adding expense: $e")))
            }
        }
    }
}