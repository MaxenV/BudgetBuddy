package com.example.budget_buddy_android.login_register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budget_buddy_android.api.ApiClient.apiService
import com.example.budget_buddy_android.models.LoginRequest
import com.example.budget_buddy_android.models.User
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel : ViewModel() {
    private var _email = mutableStateOf("")
    var email: State<String> = _email

    private var _password = mutableStateOf("")
    var password: State<String> = _password

    var errorMess by mutableStateOf("")


    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun loginUser() {
        val login = LoginRequest(_email.value, _password.value)

        viewModelScope.launch {
            try {
                val response = apiService.login(login)
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