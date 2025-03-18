package com.example.features.profile.screen.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.features.R
import com.example.features.common.view.TopBarScreen
import com.example.features.profile.model.ProfileEvent
import com.example.features.profile.viewmodel.ProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(viewModel: ProfileViewModel, userId: Int) {
    TopAppBar(
        title = {
            TopBarScreen(
                imageOnBack = R.drawable.back,
                imageDescriptionOnBack = "back",
                onBack = { viewModel.dispatch(ProfileEvent.OnClickBack(userId)) },
                onClick = { viewModel.dispatch(ProfileEvent.OnClickSettings) },
                onExit = { viewModel.dispatch(ProfileEvent.OnClickExit) }
            )
        }
    )
}