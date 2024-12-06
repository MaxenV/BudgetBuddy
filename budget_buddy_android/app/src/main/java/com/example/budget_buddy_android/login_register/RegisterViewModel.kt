package com.example.budget_buddy_android.login_register

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.budget_buddy_android.api.UserRepository
import com.example.budget_buddy_android.models.RegisterRequest
import com.example.budget_buddy_android.navigation.Screen

class RegisterViewModel : ViewModel() {
    private var _fullName = mutableStateOf("")
    var fullName: State<String> = _fullName

    private var _email = mutableStateOf("")
    var email: State<String> = _email

    private var _password = mutableStateOf("")
    var password: State<String> = _password

    private var _cpassword = mutableStateOf("")
    var cpassword: State<String> = _cpassword

    var errorMess by mutableStateOf("")

    fun updateFullName(name: String) {
        _fullName.value = name
    }

    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun updateRPassword(rpassword: String) {
        _cpassword.value = rpassword
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
        return passwordPattern.matches(password)
    }

    fun validateInputs(): Boolean {
        return when {
            _fullName.value.isEmpty() -> {
                errorMess = "Full name cannot be empty"
                false
            }
            !isEmailValid(_email.value) -> {
                errorMess = "Invalid email address"
                false
            }
            !isPasswordValid(_password.value) -> {
                errorMess = "Password must be at least 8 characters long and contain both letters and numbers"
                false
            }
            _password.value != _cpassword.value -> {
                errorMess = "Passwords do not match"
                false
            }
            else -> {
                errorMess = ""
                true
            }
        }
    }

    fun registerUser(userRepository: UserRepository, context: Context, navController:NavController) {
        if (validateInputs()) {
            val registerRequest = RegisterRequest(_fullName.value, _email.value, _password.value)
            userRepository.registerUser(registerRequest, viewModelScope) { result ->
                result.onSuccess {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.LoginScreen.route)
                }.onFailure {
                    val mess = it.message ?: "Unknown error"
                    Toast.makeText(context, mess, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}