package com.example.budget_buddy_android.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.budget_buddy_android.R
import com.example.budget_buddy_android.api.UserRepository
import com.example.budget_buddy_android.navigation.Screen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    title: String,
    config: TopBarConf = TopBarConf(),
    viewModelScope: CoroutineScope
) {
    val userRepository = UserRepository()
    val context = LocalContext.current

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (config.navigationBack) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_foreground),
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {

            if (config.addExpense) {
                IconButton(onClick = {
                    navController.navigate(Screen.AddExpenseScreen.route)
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_foreground),
                        contentDescription = "Add Expense"
                    )
                }
            }

            if (config.logout) {
                IconButton(onClick = {
                    userRepository.logoutUser(
                        viewModelScope, { result ->
                            result.onSuccess {
                                Toast.makeText(context, "Logout successful", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate(Screen.LoginScreen.route) {
                                    popUpTo(Screen.LoginScreen.route) { inclusive = true }
                                }
                            }.onFailure {
                                Toast.makeText(
                                    context,
                                    "Logout failed: ${it.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d("LOGOUT FAIL", "TopBar: $it")
                            }
                        }
                    )
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_logout_foreground),
                        contentDescription = "Logout"
                    )
                }
            }
        }
    )
}

data class TopBarConf(
    val navigationBack: Boolean = true,
    val addExpense: Boolean = true,
    val logout: Boolean = true
)