package com.example.features.registration.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.features.common.view.ButtonNavigateToView
import com.example.features.common.view.InputField
import com.example.features.registration.model.RegistrationEvent
import com.example.features.registration.model.RegistrationSideEffect
import com.example.features.registration.screen.view.RegistrationPreview
import com.example.features.registration.viewmodel.RegistrationViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

@Composable
fun RegistrationScreen(navController: NavController) {
    val viewModel: RegistrationViewModel = getViewModel()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { event ->
            when (event) {
                is RegistrationSideEffect.NavigateTo -> navController.navigate(event.route)
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

        ButtonNavigateToView(
            enabled = login.isNotEmpty() && password.isNotEmpty(),
            onClick = { viewModel.dispatch(RegistrationEvent.CreateUser(login, password)) }
        )
    }
}