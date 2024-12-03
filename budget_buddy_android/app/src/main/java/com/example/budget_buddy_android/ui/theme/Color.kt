package com.example.budget_buddy_android.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


sealed class CustomColor {
    companion object {
        val MainBlue: Color =  Color(0xFF5060B4)
        val TextWhite: Color = Color(0xFFE7E7E7)
        val FocusedBlue: Color = Color(0xFF3D51C5)
        val FocusedWhite: Color = Color(0xD8B6B6B6)
    }
}