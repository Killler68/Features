package com.example.features.profile.screen.view

import androidx.compose.runtime.Composable
import com.example.features.common.database.profile.model.Profile
import com.example.features.profile.model.Option
import com.example.features.profile.model.ProfileState
import com.example.features.profile.viewmodel.ProfileViewModel


@Composable
fun ProfileIsEnabledOption(profile: Profile, state: ProfileState, viewModel: ProfileViewModel) {

    when (state.isEditing) {
        is Option.Enabled -> {
            ProfileEditingAdditionalInfo(state, viewModel)
            BottomEditingButtons(viewModel)

        }

        is Option.Off -> {
            ProfilePreviewInfo(profile)
            ProfileAdditionalInfo(profile)
        }
    }
}