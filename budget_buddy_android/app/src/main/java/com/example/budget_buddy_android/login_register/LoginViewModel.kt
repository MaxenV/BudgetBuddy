package com.example.budget_buddy_android.login_register

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

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
         //Login functionality
    }
}