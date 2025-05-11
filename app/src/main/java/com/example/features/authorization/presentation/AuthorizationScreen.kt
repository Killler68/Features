package com.example.features.authorization.presentation

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
import com.example.features.authorization.presentation.models.AuthorizationEvent
import com.example.features.authorization.presentation.models.AuthorizationSideEffect
import com.example.features.authorization.presentation.view.AuthorizationPreview
import com.example.features.common.navigation.Screens
import com.example.features.common.strings.toast
import com.example.features.common.view.ButtonNavigateToView
import com.example.features.common.view.InputField
import org.koin.androidx.compose.getViewModel

@Composable //  no @Preview
fun AuthorizationScreen(navController: NavController) {

    val viewModel: AuthorizationViewModel = getViewModel()
    val context = LocalContext.current
    val effectFlow = viewModel.effect

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is AuthorizationSideEffect.ToFeatures -> navController.navigate(
                    Screens.Features.createRoute(effect.userId)
                )

                AuthorizationSideEffect.ToRegistration -> navController.navigate(Screens.Registration.route)
                is AuthorizationSideEffect.ErrorMessage -> toast(context, effect.error)
            }
        }
    }
    AuthorizationContent(viewModel)
}

@Composable
fun AuthorizationContent(viewModel: AuthorizationViewModel) {  // callbacks instead of passing viewModel

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AuthorizationPreview(viewModel)

        InputField(label = stringResource(R.string.login), value = login) { login = it }
        InputField(label = stringResource(R.string.password), value = password) { password = it }

        ButtonNavigateToView(
            enabled = login.isNotEmpty() && password.isNotEmpty(),
            onClick = { viewModel.dispatch(AuthorizationEvent.User(login, password)) }
        )
    }
}