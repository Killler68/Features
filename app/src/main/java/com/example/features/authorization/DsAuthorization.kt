package com.example.features.authorization

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
import com.example.features.MainScreenEvent
import com.example.features.MainViewModel
import com.example.features.navigation.Screens

@Composable
fun DsAuthorization(
    navController: NavController,
    mainViewModel: MainViewModel
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Добро пожаловать",
                fontSize = 30.sp
            )

        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Нет учетной записи? \nЗарегистрироваться",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screens.Registration.route)
                        mainViewModel.handleEvent(MainScreenEvent.NavigateToRegistration)

                    }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Text(
                text = "Логин",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(horizontal = 10.dp)

            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(
                text = "Пароль",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { },
                Modifier
                    .size(width = 350.dp, 55.dp),

                ) {
                Text(
                    text = "ГОТОВО",
                    fontSize = 20.sp
                )
            }
        }
        Box(modifier = Modifier.weight(0.2f))
    }
}