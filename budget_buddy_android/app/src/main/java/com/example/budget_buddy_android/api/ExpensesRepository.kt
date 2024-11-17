package com.example.budget_buddy_android.api

import android.util.Log
import com.example.budget_buddy_android.api.ApiClient.apiService
import com.example.budget_buddy_android.exceptions.LoginException
import com.example.budget_buddy_android.exceptions.RegistrationException
import com.example.budget_buddy_android.models.Expense
import com.example.budget_buddy_android.models.RegisterRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException

class ExpensesRepository {
    fun fetchAllExpenses(
        viewModelScope: CoroutineScope,
        onResult: (Result<List<Expense>>) -> Unit
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
}