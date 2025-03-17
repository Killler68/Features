package com.example.features.common.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ErrorMessageView(message: String) {
    Text(
        text = message,
        color = Color.Red,
        fontSize = 24.sp
    )
}