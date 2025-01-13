package com.example.features.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.common.design.TopBarScreen
import com.example.features.navigation.Screens
import com.example.features.settings.screen.SettingsCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarScreen(
                        R.drawable.back,
                        "back",
                        { navController.navigate(Screens.Features.route) },
                        "Настройки"
                    )
                },
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                SettingsCard(
                    R.drawable.trash_bucket,
                    "theme",
                    "Тема",
                    "Светлая тема"
                )
                SettingsCard(
                    R.drawable.trash_bucket,
                    "languages",
                    "Смена языка",
                    "Русский"
                )
                SettingsCard(
                    R.drawable.trash_bucket,
                    "dimensions",
                    "Размеры текстов",
                    "Обычный"
                )
            }
        }
    )
}
