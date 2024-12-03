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
import com.example.budget_buddy_android.exceptions.RegistrationException
import com.example.budget_buddy_android.models.RegisterRequest

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

    fun registerUser(userRepository: UserRepository, context: Context) {
        if (_password.value == _cpassword.value) {
            val registerRequest = RegisterRequest(_fullName.value, _email.value, _password.value)
            userRepository.registerUser(registerRequest, viewModelScope) { result ->
                result.onSuccess {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }.onFailure {
                    val mess = it.message ?: "Unknown error"
                    Toast.makeText(context, mess, Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            errorMess = "Passwords are not equal"
        }
    }
}