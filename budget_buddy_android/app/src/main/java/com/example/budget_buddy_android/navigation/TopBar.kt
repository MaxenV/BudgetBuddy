package com.example.budget_buddy_android.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.budget_buddy_android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, title:String, onLogout: () -> Unit, onAddExpense: () -> Unit) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_foreground), contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = onAddExpense) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_foreground), contentDescription = "Add Expense")
            }
            IconButton(onClick = onLogout) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_logout_foreground), contentDescription = "Logout")
            }
        }
    )
}