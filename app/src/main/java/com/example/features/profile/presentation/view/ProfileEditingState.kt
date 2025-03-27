package com.example.features.profile.presentation.view

import androidx.compose.runtime.Composable
import com.example.features.profile.data.database.model.Profile
import com.example.features.profile.domain.entities.Option
import com.example.features.profile.presentation.models.ProfileState
import com.example.features.profile.presentation.ProfileViewModel


@Composable
fun ProfileEditingState(profile: Profile, state: ProfileState, viewModel: ProfileViewModel ) {

    when (state.isOption) {
        is Option.Enabled -> {
            ProfileEditingDetails(state, viewModel)
            ProfileEditingButtons(viewModel)
        }

        is Option.Off -> {
            ProfilePreview(profile)
            ProfileAdditionalDetails(profile)
        }
    }
}