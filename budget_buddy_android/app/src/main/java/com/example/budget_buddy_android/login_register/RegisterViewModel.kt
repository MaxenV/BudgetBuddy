package com.example.budget_buddy_android.login_register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budget_buddy_android.api.ApiClient.apiService
import com.example.budget_buddy_android.models.User
import kotlinx.coroutines.launch
import retrofit2.HttpException

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
        _cpassword.value = rpassword
    }

    fun registerUser() {
        if (_password.value == _cpassword.value){

            val user = User(_fullName.value, _email.value, _password.value)

            viewModelScope.launch {
                try {
                    val response = apiService.register(user)
                    if (response.isSuccessful) {
                        val registeredUser = response.body()
                        Log.d("API", "registerUser: Registration successful, user: $registeredUser")
                    } else {
                        Log.d("API", "registerUser: Registration failed with code ${response.code()}, error: ${response.errorBody()?.string()}")
                    }
                } catch (e: HttpException) {
                    Log.d("API", "registerUser: HTTP error ${e.message}")
                } catch (e: Exception) {
                    Log.d("API", "registerUser: Error register request $e")
                }
            }
        }
        else {
            errorMess = "Passwords are not equals"
        }
    }
}