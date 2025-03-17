package com.example.features.authorization.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.features.authorization.model.AuthorizationEvent
import com.example.features.authorization.model.AuthorizationSideEffect
import com.example.features.authorization.model.AuthorizationState
import com.example.features.authorization.screen.view.AuthorizationPreview
import com.example.features.authorization.viewmodel.AuthorizationViewModel
import com.example.features.common.strings.toast
import com.example.features.common.view.ButtonNavigateToView
import com.example.features.common.view.InputField
import com.example.features.navigation.Screens
import org.koin.androidx.compose.getViewModel

@Composable
fun AuthorizationScreen(navController: NavController) {

    val viewModel: AuthorizationViewModel = getViewModel()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val effectFlow = viewModel.effect

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is AuthorizationSideEffect.ToFeatures -> navController.navigate(
                    Screens.Features.createRoute(effect.userId)
                )

                AuthorizationSideEffect.ToRegistration -> navController.navigate(Screens.Registration.route)
            }
        }
    }
    when (state) {
        is AuthorizationState.Error -> {
            val message = (state as AuthorizationState.Error).message
            LaunchedEffect(message) { toast(context, message) }
        }

        AuthorizationState.Loading -> {}
        AuthorizationState.Success -> {}
    }
    AuthorizationContent(viewModel)
}

@Composable
fun AuthorizationContent(viewModel: AuthorizationViewModel) {

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AuthorizationPreview(viewModel)

        InputField("Логин", login) { login = it }
        InputField("Пароль", password) { password = it }

        ButtonNavigateToView(
            enabled = login.isNotEmpty() && password.isNotEmpty(),
            onClick = { viewModel.dispatch(AuthorizationEvent.User(login, password)) }
        )
    }
}