package com.example.features.settings.presentation.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.features.R
import com.example.features.common.view.TopBarScreen
import com.example.features.settings.presentation.SettingsViewModel
import com.example.features.settings.presentation.models.SettingsEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(viewModel: SettingsViewModel, userId: Int) {

    TopAppBar(
        title = {
            TopBarScreen(
                R.drawable.back,
                stringResource(R.string.back_image_description),
                { viewModel.dispatch((SettingsEvent.OnBack(userId))) },
                stringResource(R.string.settings)
            )
        }
    )
}