package com.example.budget_buddy_android.login_register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.budget_buddy_android.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
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

                OutlinedTextField(
                    value = viewModel.fullName.value,
                    onValueChange = { viewModel.updateFullName(it) },
                    label = { Text("Full name") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = CustomColor.TextWhite,
                        unfocusedTextColor = CustomColor.TextWhite,
                        unfocusedLabelColor = CustomColor.TextWhite,
                        focusedLabelColor = CustomColor.TextWhite,
                        focusedBorderColor = CustomColor.FocusedWhite
                    )
                )
                OutlinedTextField(
                    value = viewModel.email.value,
                    onValueChange = { viewModel.updateEmail(it) },
                    label = { Text("Email") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = CustomColor.TextWhite,
                        unfocusedTextColor = CustomColor.TextWhite,
                        unfocusedLabelColor = CustomColor.TextWhite,
                        focusedLabelColor = CustomColor.TextWhite,
                        focusedBorderColor = CustomColor.FocusedWhite
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = viewModel.password.value,
                    onValueChange = { viewModel.updatePassword(it) },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = CustomColor.TextWhite,
                        unfocusedTextColor = CustomColor.TextWhite,
                        unfocusedLabelColor = CustomColor.TextWhite,
                        focusedLabelColor = CustomColor.TextWhite,
                        focusedBorderColor = CustomColor.FocusedWhite
                    )
                )
                OutlinedTextField(
                    value = viewModel.cpassword.value,
                    onValueChange = { viewModel.updateRPassword(it) },
                    label = { Text("Repeat password") },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = CustomColor.TextWhite,
                        unfocusedTextColor = CustomColor.TextWhite,
                        unfocusedLabelColor = CustomColor.TextWhite,
                        focusedLabelColor = CustomColor.TextWhite,
                        focusedBorderColor = CustomColor.FocusedWhite
                    )
                )

                if (viewModel.errorMess.isNotEmpty()) {
                    Text(viewModel.errorMess, color = Color.Red)
                }

                Spacer(modifier = Modifier.height(15.dp))
                Button(onClick = { viewModel.registerUser(userRepository, context,navController) }) {
                    Text("Register me")
                }
                Text(text = "Do you have already an account? Login here",
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