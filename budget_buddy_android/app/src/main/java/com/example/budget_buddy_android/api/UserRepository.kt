package com.example.budget_buddy_android.api

import android.util.Log
import com.example.budget_buddy_android.api.ApiClient.apiService
import com.example.budget_buddy_android.exceptions.LoginException
import com.example.budget_buddy_android.exceptions.RegistrationException
import com.example.budget_buddy_android.models.LoginRequest
import com.example.budget_buddy_android.models.RegisterRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException

class UserRepository {
    fun registerUser(
        registerRequest: RegisterRequest,
        viewModelScope: CoroutineScope,
        onResult: (Result<String>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = apiService.register(registerRequest)
                if (response.isSuccessful) {
                    response.body()
                    onResult(Result.success("Registration successful"))
                } else {
                    val errorBody = response.errorBody()?.string()
                    if (errorBody != null && errorBody.contains("Duplicate entry")) {
                        onResult(Result.failure(RegistrationException("User already exists")))
                    } else {
                        onResult(Result.failure(RegistrationException("Registration failed with code ${response.code()}, error: $errorBody")))
                    }
                }
            } catch (e: HttpException) {
                onResult(Result.failure(RegistrationException("HTTP error: ${e.message}")))
            } catch (e: ConnectException) {
                onResult(Result.failure(LoginException("Cannot connect to server")))
            } catch (e: Exception) {
                onResult(Result.failure(RegistrationException("Error register request: $e")))
            }
        }
    }

    fun loginUser(
        loginRequest: LoginRequest,
        viewModelScope: CoroutineScope,
        onResult: (Result<String>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = apiService.login(loginRequest)
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        ApiClient.setToken("Bearer ${loginResponse.token}")
                        onResult(Result.success("Login successful"))
                    } else {
                        onResult(Result.failure(LoginException("Login response is null")))
                    }
                } else {
                    if (response.code() == 401) {
                        onResult(Result.failure(LoginException("Bad email or password")))
                    } else {
                        onResult(Result.failure(LoginException("Unknown error: ${response.code()}")))
                        Log.d(
                            "API", "loggedUser: Login failed with code ${response.code()}, error: ${
                                response.errorBody()?.string()
                            }"
                        )
                    }
                }
            } catch (e: HttpException) {
                onResult(Result.failure(LoginException("HTTP error: ${e.message}")))
            } catch (e: ConnectException) {
                onResult(Result.failure(LoginException("Cannot connect to server")))
            } catch (e: Exception) {
                Log.d("API ERR", "loginUser: $e")
                onResult(Result.failure(LoginException("Error login request: $e")))
            }
        }
    }
}
