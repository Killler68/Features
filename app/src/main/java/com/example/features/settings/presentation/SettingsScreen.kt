package com.example.features.settings.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.R
import com.example.features.settings.presentation.models.SettingsEvent
import com.example.features.settings.presentation.models.SettingsSideEffect
import com.example.features.settings.presentation.view.SettingsCardView
import com.example.features.settings.presentation.view.SettingsTopBar
import com.example.features.ui.theme.Cyan
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(navController: NavController, userId: Int) {

    val viewModel: SettingsViewModel = getViewModel()
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { event ->
            when (event) {
                is SettingsSideEffect.NavigateTo -> navController.navigate(event.route)
                is SettingsSideEffect.ErrorMessage -> event.message
            }
        }
    }
    SettingsContent(viewModel, userId)
}

@Composable
fun SettingsContent(viewModel: SettingsViewModel, userId: Int) {

    Scaffold(
        topBar = { SettingsTopBar(viewModel, userId) },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                SettingsCardView(
                    imageCard = R.drawable.trash_bucket,
                    imageDescription = stringResource(R.string.theme_image_description),
                    title = stringResource(R.string.theme),
                    subTitle = stringResource(R.string.light_theme)
                )
                SettingsCardView(
                    imageCard = R.drawable.trash_bucket,
                    imageDescription = stringResource(R.string.languages_image_description),
                    title = stringResource(R.string.change_languages),
                    subTitle = stringResource(R.string.russian_languages)
                )
                SettingsCardView(
                    imageCard = R.drawable.trash_bucket,
                    imageDescription = stringResource(R.string.dimension_image_description),
                    title = stringResource(R.string.text_size),
                    subTitle = stringResource(R.string.ordinary)
                )
                Text(
                    text = stringResource(R.string.delete_user),
                    fontSize = 18.sp,
                    color = Cyan,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .clickable { viewModel.dispatch(SettingsEvent.DeleteUser(userId)) }
                )
            }
        }
    )
}
