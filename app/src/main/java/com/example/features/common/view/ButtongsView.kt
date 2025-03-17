package com.example.features.common.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.ui.theme.Cyan

@Composable
fun ButtonsApply(onClick: () -> Unit, textButton: String) {
    Button(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Cyan),
        onClick = { onClick() }
    ) {
        Text(
            text = textButton,
            fontSize = 16.sp
        )
    }
}