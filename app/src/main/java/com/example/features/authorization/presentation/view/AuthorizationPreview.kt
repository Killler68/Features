package com.example.features.authorization.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.features.authorization.presentation.models.AuthorizationEvent
import com.example.features.authorization.presentation.AuthorizationViewModel
import com.example.features.common.view.TextNavigateToView

@Composable
fun AuthorizationPreview(viewModel: AuthorizationViewModel) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
    )
    Text(
        text = "Добро пожаловать",
        fontSize = 30.sp
    )
    TextNavigateToView(
        title = "Нет учетной записи? \n",
        subTitle = "Зарегистрироваться",
        onClick = { viewModel.dispatch(AuthorizationEvent.NavigateToRegistration) }
    )
}