package com.example.budget_buddy_android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.login_register.RegisterView

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = Screen.RegisterScreen.route
    ){
        composable(Screen.RegisterScreen.route){
            RegisterView()
        }
    }
}