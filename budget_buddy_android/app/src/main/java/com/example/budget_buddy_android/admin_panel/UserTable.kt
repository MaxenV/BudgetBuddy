package com.example.budget_buddy_android.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.models.User
import com.example.budget_buddy_android.navigation.Screen

@Composable
fun UsersTable(users: List<User>, navController:NavController) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                "ID",
                modifier = Modifier
                    .width(30.dp)
                    .wrapContentWidth(Alignment.Start)
                    .padding(start = 5.dp)
            )
            Text(
                "Name",
                modifier = Modifier
                    .width(150.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Text("email", modifier = Modifier
                .width(170.dp)
                .wrapContentWidth(Alignment.End)
                .padding(end = 5.dp)
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            items(users) { user ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(5.dp, 10.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.UserDetailScreen.createRoute(user.id))
                        }
                ) {
                    Text(
                        user.id.toString(),
                        modifier = Modifier
                            .width(50.dp)
                            .wrapContentWidth(Alignment.Start)
                    )
                    Text(
                        user.fullName,
                        modifier = Modifier
                            .width(150.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                    Text(
                        user.username,
                        modifier = Modifier
                            .width(170.dp)
                            .wrapContentWidth(Alignment.End)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun UsersTablePreview(){
    val usersList = listOf(
        User( 1,"Admin", "admin@example.com", true, "admin@example.com",
        )
    )
    val navController: NavController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            UsersTable(usersList,navController)
        }
    }
}