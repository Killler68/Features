package com.example.features.common.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun ErrorMessageView(message: Int) {
    Text(
        text = stringResource(message),
        color = Color.Red,
        fontSize = 24.sp
    )
}