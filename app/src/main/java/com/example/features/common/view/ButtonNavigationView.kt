package com.example.features.common.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.strings.toast
import com.example.features.ui.theme.Cyan


@Composable
fun ButtonNavigateToView(enabled: Boolean, onClick: () -> Unit) {
    val context = LocalContext.current

    Button(
        onClick = {
            if (enabled) {
                onClick()
            } else toast(context, "Введите логин и пароль")
        },
        Modifier
            .padding(top = 20.dp)
            .size(width = 350.dp, 55.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Cyan),
    ) {
        Text(
            text = "ГОТОВО",
            fontSize = 20.sp
        )
    }
}