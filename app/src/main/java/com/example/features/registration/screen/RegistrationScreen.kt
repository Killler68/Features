package com.example.features.registration.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.common.view.InputField
import com.example.features.navigation.Screens
import com.example.features.registration.model.RegistrationEvent
import com.example.features.registration.screen.view.RegistrationPreview
import com.example.features.registration.viewmodel.RegistrationViewModel
import com.example.features.ui.theme.Cyan
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

@Composable
fun RegistrationScreen(navController: NavController) {
    val viewModel: RegistrationViewModel = getViewModel()

    LaunchedEffect(Unit) {
        viewModel.event.collectLatest { event ->
            val route = when (event) {
                RegistrationEvent.CreateUser -> Screens.Features.route
                RegistrationEvent.NavigateToAuthorization -> Screens.Authorization.route
            }
            navController.navigate(route) {
                if (event is RegistrationEvent.CreateUser) popUpTo(Screens.Registration.route) {
                    inclusive = true
                }
            }
        }
    }
    RegistrationContent(viewModel)
}

@Composable
fun RegistrationContent(viewModel: RegistrationViewModel) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegistrationPreview()

        InputField("Логин", login) { login = it }
        InputField("Пароль", password) { password = it }

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
                        viewModel.dispatch(
                            event = RegistrationEvent.CreateUser,
                            login = login,
                            password = password
                        )
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
    }
}