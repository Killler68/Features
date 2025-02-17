package com.example.features.welcome.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.navigation.Screens
import com.example.features.ui.theme.Cyan
import com.example.features.welcome.models.WelcomeEvent
import com.example.features.welcome.models.WelcomeSideEffect
import com.example.features.welcome.models.WelcomeState
import com.example.features.welcome.viewmodel.WelcomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DsWelcome(navController: NavController) {

    val viewModel: WelcomeViewModel = getViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { event ->
            when (event) {
                WelcomeSideEffect.ToAuthorization -> navController.navigate(Screens.Authorization.route)
                WelcomeSideEffect.ToRegistration -> navController.navigate(Screens.Registration.route)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Приветствую в ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 24.sp
                        )
                    )
                    { append("сборнике приложений") }
                },
                fontSize = 16.sp
            )
        }

        when (state) {
            is WelcomeState.Loading -> CircularProgressIndicator()
            is WelcomeState.Success -> WelcomePager((state as WelcomeState.Success).items)
            is WelcomeState.Error -> ErrorMessage((state as WelcomeState.Error).message)
        }

        Button(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Cyan),
            onClick = { viewModel.dispatch(WelcomeEvent.ToRegistration) }

        ) {
            Text(
                text = "Создать аккаунт",
                fontSize = 16.sp
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Cyan),
            onClick = { viewModel.dispatch(WelcomeEvent.ToAuthorization) }
        ) {
            Text(
                text = "Авторизация",
                fontSize = 16.sp
            )
        }
    }
}