package com.example.features.registration.presentation

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.strings.toast
import com.example.features.common.view.ButtonNavigateToView
import com.example.features.common.view.InputField
import com.example.features.registration.presentation.models.RegistrationEvent
import com.example.features.registration.presentation.models.RegistrationSideEffect
import com.example.features.registration.presentation.view.RegistrationPreview
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

@Composable
fun RegistrationScreen(navController: NavController) {
    val viewModel: RegistrationViewModel = getViewModel()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is RegistrationSideEffect.NavigateTo -> navController.navigate(effect.route)
                is RegistrationSideEffect.ErrorMessage -> toast(context, effect.message)
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

        InputField(label = stringResource(R.string.login), value = login) { login = it }
        InputField(label = stringResource(R.string.password), value = password) { password = it }

        ButtonNavigateToView(
            enabled = login.isNotEmpty() && password.isNotEmpty(),
            onClick = { viewModel.dispatch(RegistrationEvent.CreateUser(login, password)) }
        )
    }
}