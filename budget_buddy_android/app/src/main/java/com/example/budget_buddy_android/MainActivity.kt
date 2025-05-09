package com.example.budget_buddy_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.budget_buddy_android.api.UserRepository
import com.example.budget_buddy_android.navigation.Navigation
import com.example.budget_buddy_android.ui.theme.Budget_buddy_androidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Budget_buddy_androidTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Navigation()
                }
            }
        }
    }
}
