package com.example.features.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.features.registration.viewmodel.RegistrationViewModel
import com.example.features.ui.theme.Cyan
import org.koin.androidx.compose.getViewModel

@Composable
fun DsRegistration(navController: NavController) {

    val mainViewModel: MainViewModel = getViewModel()
    val registrationViewModel: RegistrationViewModel = getViewModel()

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
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
                    .clickable {
                        navController.navigate(Screens.Authorization.route)
                        mainViewModel.handleEvent(MainScreenEvent.NavigateToAuthorization)
                    }
            )
        }
        TextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Логин") },
            colors = OutlinedTextFieldDefaults.colors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            colors = OutlinedTextFieldDefaults.colors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    if (login.isNotEmpty() && password.isNotEmpty()) {
                        registrationViewModel.createUser(login, password)
                        navController.navigate(Screens.Features.route)
                        mainViewModel.handleEvent(MainScreenEvent.NavigateToFeatures)
                    }
                },
                Modifier
                    .size(width = 350.dp, 55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Cyan)
            ) {
                Text(
                    text = "ГОТОВО",
                    fontSize = 20.sp,
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