package com.example.features.settings.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.settings.model.SettingsEvent
import com.example.features.settings.model.SettingsSideEffect
import com.example.features.settings.screen.view.SettingsCardView
import com.example.features.settings.screen.view.SettingsTopBar
import com.example.features.settings.viewmodel.SettingsViewModel
import com.example.features.ui.theme.Cyan
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(navController: NavController) {

    val viewModel: SettingsViewModel = getViewModel()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { event ->
            when (event) {
                is SettingsSideEffect.NavigateTo -> navController.navigate(event.route)
            }
        }
    }
    SettingsContent(viewModel)
}

@Composable
fun SettingsContent(viewModel: SettingsViewModel) {

    Scaffold(
        topBar = { SettingsTopBar(viewModel) },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                SettingsCardView(
                    R.drawable.trash_bucket,
                    "theme",
                    "Тема",
                    "Светлая тема"
                )
                SettingsCardView(
                    R.drawable.trash_bucket,
                    "languages",
                    "Смена языка",
                    "Русский"
                )
                SettingsCardView(
                    R.drawable.trash_bucket,
                    "dimensions",
                    "Размеры текстов",
                    "Обычный"
                )
                Text(
                    text = "Удалить пользователя",
                    fontSize = 18.sp,
                    color = Cyan,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .clickable { viewModel.dispatch(SettingsEvent.DeleteUser) }
                )
            }
        }
    )
}
