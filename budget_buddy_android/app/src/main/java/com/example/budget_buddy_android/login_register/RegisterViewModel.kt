package com.example.budget_buddy_android.login_register

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.budget_buddy_android.models.User

class RegisterViewModel : ViewModel() {
    private var _fullName = mutableStateOf("")
    var fullName: State<String> = _fullName

    private var _email = mutableStateOf("")
    var email: State<String> = _email

    private var _password = mutableStateOf("")
    var password: State<String> = _password

    private var _rpassword = mutableStateOf("")
    var rpassword: State<String> = _rpassword

    fun updateFullName(name: String){
        _fullName.value = name
    }

    fun updateEmail(email: String){
        _email.value = email
    }

    fun updatePassword(password: String){
        _password.value = password
    }

    fun updateRPassword(rpassword: String){
        _rpassword.value = rpassword
    }
}