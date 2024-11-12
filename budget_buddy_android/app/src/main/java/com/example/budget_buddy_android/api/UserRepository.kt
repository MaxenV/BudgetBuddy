package com.example.budget_buddy_android.api

import android.util.Log
import com.example.budget_buddy_android.api.ApiClient.apiService
import com.example.budget_buddy_android.models.LoginRequest
import com.example.budget_buddy_android.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserRepository {
    fun registerUser(user: User, viewModelScope: CoroutineScope) {
        viewModelScope.launch {
            try {
                val response = apiService.register(user)
                if (response.isSuccessful) {
                    val registeredUser = response.body()
                    Log.d("API", "registerUser: Registration successful, user: $registeredUser")
                } else {
                    val errorBody = response.errorBody()?.string()
                    if (errorBody != null && errorBody.contains("Duplicate entry")) {
                        throw Exception("User already exists")
                    } else {
                        throw Exception("Registration failed with code ${response.code()}, error: $errorBody")
                    }
                }
            } catch (e: HttpException) {
                throw Exception("HTTP error: ${e.message}")
            } catch (e: Exception) {
                throw Exception("Error register request: $e")
            }
        }
    }

    fun loginUser(loginRequest: LoginRequest, viewModelScope: CoroutineScope){
        viewModelScope.launch {
            try {
                val response = apiService.login(loginRequest)
                if (response.isSuccessful) {
                    val loggedUser = response.body()
                    Log.d("API", "loggedUser: Login successful, user: $loggedUser")
                } else {
                    Log.d("API", "loggedUser: Login failed with code ${response.code()}, error: ${response.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                Log.d("API", "loggedUser: HTTP error ${e.message}")
            } catch (e: Exception) {
                Log.d("API", "loggedUser: Error login request $e")
            }
        }
    }
}
