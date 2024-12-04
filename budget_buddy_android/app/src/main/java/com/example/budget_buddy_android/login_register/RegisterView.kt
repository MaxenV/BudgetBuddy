package com.example.budget_buddy_android.login_register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budget_buddy_android.ui.theme.CustomColor
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budget_buddy_android.api.UserRepository
import com.example.budget_buddy_android.navigation.Screen


@Composable
fun RegisterView(
    navController: NavController, userRepository: UserRepository
) {
    val viewModel: RegisterViewModel = viewModel()
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier.background(color = CustomColor.MainBlue)
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Registration", fontSize = 24.sp, color = Color.White)
                Spacer(modifier = Modifier.height(10.dp))

                RegisterRow("Full name", viewModel.fullName.value, { viewModel.updateFullName(it) })
                RegisterRow("Email", viewModel.email.value, { viewModel.updateEmail(it) })
                Spacer(modifier = Modifier.height(10.dp))

                RegisterRow("Password", viewModel.password.value, { viewModel.updatePassword(it) })
                RegisterRow("Repeat password",
                    viewModel.cpassword.value,
                    { viewModel.updateRPassword(it) })

                if (viewModel.errorMess.isNotEmpty()) {
                    Text(viewModel.errorMess)
                }

                Spacer(modifier = Modifier.height(15.dp))
                Button(onClick = { viewModel.registerUser(userRepository,context) }) {
                    Text("Register me")
                }
                Text(text = "If you have account? Login here",
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.LoginScreen.route)
                        }
                        .padding(10.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterRow(
    label: String, value: String, onValueChange: (String) -> Unit
) {
    Row {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = CustomColor.TextWhite,
                unfocusedTextColor = CustomColor.TextWhite,
                unfocusedLabelColor = CustomColor.TextWhite,
                focusedLabelColor = CustomColor.TextWhite,
                focusedBorderColor = CustomColor.FocusedWhite
            )
        )
    }
}


@Preview
@Composable
fun RegisterViewPreview() {
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            RegisterView(navController, UserRepository())
        }
    }
}