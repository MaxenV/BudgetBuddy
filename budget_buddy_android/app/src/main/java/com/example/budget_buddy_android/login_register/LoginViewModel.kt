package com.example.budget_buddy_android.login_register

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budget_buddy_android.api.UserRepository
import com.example.budget_buddy_android.models.LoginRequest

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

    fun loginUser(userRepository: UserRepository, context: Context) {
        val loginRequest = LoginRequest(_email.value, _password.value)
        userRepository.loginUser(loginRequest, viewModelScope) { result ->
            result.onSuccess {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }.onFailure {
                val mess = it.message ?: "Unknown error"
                Toast.makeText(context, mess, Toast.LENGTH_SHORT).show()
            }
        }
    }
}