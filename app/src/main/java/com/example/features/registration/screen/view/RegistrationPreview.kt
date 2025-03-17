package com.example.features.registration.screen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.features.common.view.TextNavigateToView
import com.example.features.registration.model.RegistrationEvent
import com.example.features.registration.viewmodel.RegistrationViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun RegistrationPreview() {
    val viewModel: RegistrationViewModel = getViewModel()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
    )
    Text("Регистрация", fontSize = 30.sp)
    TextNavigateToView(
        title = "Уже есть учетная запись?\n",
        subTitle = "                   Войти",
        onClick = { viewModel.dispatch(RegistrationEvent.NavigateToAuthorization) }
    )
}
