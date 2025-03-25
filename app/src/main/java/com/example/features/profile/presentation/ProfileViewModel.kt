package com.example.features.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.navigation.Screens
import com.example.features.profile.data.database.model.Profile
import com.example.features.profile.domain.entities.Option
import com.example.features.profile.domain.usecase.CreateProfileUseCase
import com.example.features.profile.domain.usecase.GetProfileByIdUseCase
import com.example.features.profile.domain.usecase.GetUserLoginUseCase
import com.example.features.profile.domain.usecase.UpdateProfileUseCase
import com.example.features.profile.presentation.models.ProfileEvent
import com.example.features.profile.presentation.models.ProfileSideEffect
import com.example.features.profile.presentation.models.ProfileState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val addProfileUseCase: CreateProfileUseCase,
    private val getUserLoginUseCase: GetUserLoginUseCase // Добавили новый UseCase
) : ViewModel() {

    private val _state: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> get() = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ProfileSideEffect>()
    val effect: SharedFlow<ProfileSideEffect> get() = _effect.asSharedFlow()

    fun dispatch(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnClickSettings -> enableEditing()
            ProfileEvent.OnClickCancel -> cancelEditing()
            ProfileEvent.OnClickApply -> applyChanges()
            ProfileEvent.OnClickExit -> navigateTo(Screens.Authorization.route)
            is ProfileEvent.OnClickBack -> navigateTo(Screens.Features.createRoute(event.userId))
            is ProfileEvent.LoadProfile -> loadProfile(event.userId)
            is ProfileEvent.OnNameChange -> updateState { copy(editName = event.value) }
            is ProfileEvent.OnAgeChange -> updateState { copy(editAge = event.value) }
            is ProfileEvent.OnCityChange -> updateState { copy(editCity = event.value) }
            is ProfileEvent.OnNationalityChange -> updateState { copy(editNationality = event.value) }
            is ProfileEvent.OnEmailChange -> updateState { copy(editEmail = event.value) }
        }
    }

    private fun loadProfile(userId: Int) {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }

            val profile = getProfileByIdUseCase(userId) ?: createProfile(userId)

            updateState {
                copy(
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

    private suspend fun createProfile(userId: Int): Profile {
        val userLogin = getUserLoginUseCase(userId)
        val newProfile = Profile(
            id = 0,
            userId = userId,
            userLogin = userLogin,
            email = "",
            name = "",
            age = "",
            city = "",
            nationality = ""
        )
        return addProfileUseCase(newProfile)
    }

    private fun applyChanges() {
        viewModelScope.launch {
            state.value.profile?.let { profile ->
                val updatedProfile = profile.copy(
                    name = _state.value.editName,
                    age = _state.value.editAge,
                    city = _state.value.editCity,
                    nationality = _state.value.editNationality,
                    email = _state.value.editEmail
                )
                updateProfileUseCase(updatedProfile)
                updateState { copy(profile = updatedProfile, isOption = Option.Off(false)) }
            }
        }
    }

    private fun enableEditing() {
        updateState { copy(isOption = Option.Enabled(true)) }
    }

    private fun cancelEditing() {
        updateState { copy(isOption = Option.Off(false)) }
    }

    private fun updateState(transform: ProfileState.() -> ProfileState) {
        _state.value = _state.value.transform()
    }

    private fun navigateTo(route: String) {
        viewModelScope.launch {
            _effect.emit(ProfileSideEffect.NavigateTo(route))
        }
    }
}