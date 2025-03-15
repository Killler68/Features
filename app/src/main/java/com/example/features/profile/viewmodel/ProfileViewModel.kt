package com.example.features.profile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.database.profile.model.Profile
import com.example.features.navigation.Screens
import com.example.features.profile.model.Option
import com.example.features.profile.model.ProfileEvent
import com.example.features.profile.model.ProfileSideEffect
import com.example.features.profile.model.ProfileState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val addProfileUseCase: CreateProfileUseCase
) : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    private val _effect = MutableSharedFlow<ProfileSideEffect>()
    val effect: SharedFlow<ProfileSideEffect> get() = _effect.asSharedFlow()

    fun handleEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.LoadProfile -> loadProfile(event.userId)
            ProfileEvent.OnClickSettings -> onClickSettings()
            ProfileEvent.OnClickCancel -> state = state.copy(isEditing = Option.Off(false))
            ProfileEvent.OnClickApply -> onApplyClick()
            ProfileEvent.OnClickBack -> onBack(Screens.Features.route)
            ProfileEvent.OnClickExit -> onBack(Screens.Features.route)
            is ProfileEvent.OnNameChange -> state = state.copy(editName = event.value)
            is ProfileEvent.OnAgeChange -> state = state.copy(editAge = event.value)
            is ProfileEvent.OnCityChange -> state = state.copy(editCity = event.value)
            is ProfileEvent.OnNationalityChange -> state = state.copy(editNationality = event.value)
            is ProfileEvent.OnEmailChange -> state = state.copy(editEmail = event.value)
        }
    }

    private fun loadProfile(userId: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val profile = getProfileByIdUseCase(userId)

            if (profile == null) {
                val newProfile = Profile(
                    id = 0,
                    userId = userId,
                    email = "",
                    name = "",
                    age = "",
                    city = "",
                    nationality = ""
                )
                val createdProfile = addProfileUseCase(newProfile)

                state = state.copy(
                    profile = createdProfile,
                    editName = createdProfile.name,
                    editAge = createdProfile.age,
                    editCity = createdProfile.city,
                    editNationality = createdProfile.nationality,
                    editEmail = createdProfile.email,
                    isLoading = false
                )
            } else {
                state = state.copy(
                    profile = profile,
                    editName = profile.name,
                    editAge = profile.age,
                    editCity = profile.city,
                    editNationality = profile.nationality,
                    editEmail = profile.email,
                    isLoading = false
                )
            }
        }
    }

    private fun onApplyClick() {
        viewModelScope.launch {
            state.profile?.let { profile ->
                val updatedProfile = profile.copy(
                    name = state.editName,
                    age = state.editAge,
                    city = state.editCity,
                    nationality = state.editNationality,
                    email = state.editEmail
                )
                updateProfileUseCase(updatedProfile)
                state = state.copy(profile = updatedProfile, isEditing = Option.Off(false))
            }
        }
    }

    private fun onClickSettings() {
        state = state.copy(isEditing = Option.Enabled(true))
    }

    private fun onBack(route: String) {
        viewModelScope.launch {
            _effect.emit(ProfileSideEffect.NavigateTo(route))
        }
    }
}



