package com.example.features.profile.presentation.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.features.R
import com.example.features.common.view.TopBarScreen
import com.example.features.profile.presentation.ProfileViewModel
import com.example.features.profile.presentation.models.ProfileEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(viewModel: ProfileViewModel, userId: Int) {
    TopAppBar(
        title = {
            TopBarScreen(
                imageOnBack = R.drawable.back,
                imageDescriptionOnBack = stringResource(R.string.back_image_description),
                onBack = { viewModel.dispatch(ProfileEvent.OnClickBack(userId)) },
                onClick = { viewModel.dispatch(ProfileEvent.OnClickSettings) },
                onExit = { viewModel.dispatch(ProfileEvent.OnClickExit) }
            )
        }
    )
}