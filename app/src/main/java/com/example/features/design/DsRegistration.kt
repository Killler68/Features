package com.example.features.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DsRegistration() {

    Column(
        Modifier
            .fillMaxSize()
    ) {
        Text(text = "Регистрация")
        Text(text = "Уже есть учетная запись? Войти")
        Text(text = "Введите имя")
        Text(text = "Введите пароль")
        Button(onClick = { /*TODO*/ }) {

        }

    }
}