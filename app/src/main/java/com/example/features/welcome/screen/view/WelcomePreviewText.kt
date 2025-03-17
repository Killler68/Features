package com.example.features.welcome.screen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.annotatedString


@Composable
fun WelcomePreviewText() {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
    ) {
        Text(
            annotatedString(
                changedText = "Приветствую в ",
                notChangedText = "Сборнике приложений",
                fontWeight = FontWeight.Bold,
                fonStyle = FontStyle.Italic,
                fontSize = 24.sp
            ), fontSize = 16.sp
        )
    }
}