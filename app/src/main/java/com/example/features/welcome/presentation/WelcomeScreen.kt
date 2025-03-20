package com.example.features.welcome.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.features.common.view.ButtonsApply
import com.example.features.common.view.ErrorMessageView
import com.example.features.welcome.presentation.models.WelcomeEvent
import com.example.features.welcome.presentation.models.WelcomeSideEffect
import com.example.features.welcome.presentation.models.WelcomeState
import com.example.features.welcome.presentation.view.WelcomePagerView
import com.example.features.welcome.presentation.view.WelcomePreviewText
import org.koin.androidx.compose.getViewModel

@Composable
fun WelcomeScreen(navController: NavController) {

    val viewModel: WelcomeViewModel = getViewModel()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { event ->
            when (event) {
                is WelcomeSideEffect.NavigateTo -> navController.navigate(event.router)
            }
        }
    }
    WelcomeContent(viewModel = viewModel)
}

@Composable
fun WelcomeContent(viewModel: WelcomeViewModel) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        WelcomePreviewText()

        when (state) {
            is WelcomeState.Loading -> CircularProgressIndicator()
            is WelcomeState.Success -> WelcomePagerView((state as WelcomeState.Success).items)
            is WelcomeState.Error -> ErrorMessageView((state as WelcomeState.Error).message)
        }

        ButtonsApply(
            onClick = { viewModel.dispatch(event = WelcomeEvent.ToRegistration) },
            textButton = "Создать аккаунт",
        )
        ButtonsApply(
            onClick = { viewModel.dispatch(event = WelcomeEvent.ToAuthorization) },
            textButton = "Авторизация",
        )
    }
}



