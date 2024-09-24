package com.example.features.design

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.navigation.Screens

@Composable
fun DsRegistration(navController: NavController) {

    Column(
        Modifier
            .fillMaxSize()

    ) {
        Box(
            modifier =
            Modifier
                .weight(0.1f)
        )
        Box(
            modifier =
            Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Регистрация",
                fontSize = 30.sp
            )
        }

        Box(
            modifier =
            Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Уже есть учетная запись? \nВойти",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable { navController.navigate(Screens.AuthorizationFragment.route) }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Text(
                text = "Введите имя",
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                fontSize = 16.sp

            )

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(
                text = "Введите пароль",
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                fontSize = 16.sp

            )

        }

        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate(Screens.AuthorizationFragment.route) },
                Modifier
                    .size(width = 350.dp, 55.dp)

            ) {
                Text(
                    text = "ГОТОВО",
                    fontSize = 20.sp
                )
            }

        }
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .weight(0.2f)
        )

    }
}