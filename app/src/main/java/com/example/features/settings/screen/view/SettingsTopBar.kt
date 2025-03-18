package com.example.features.settings.screen.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.features.R
import com.example.features.common.view.TopBarScreen
import com.example.features.settings.model.SettingsEvent
import com.example.features.settings.viewmodel.SettingsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(viewModel: SettingsViewModel) {

    TopAppBar(
        title = {
            TopBarScreen(
                R.drawable.back,
                "back",
                { viewModel.dispatch((SettingsEvent.OnBack)) },
                "Настройки"
            )
        }
    )
}