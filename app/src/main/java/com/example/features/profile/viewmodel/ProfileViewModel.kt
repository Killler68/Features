package com.example.features.profile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.database.profile.model.Profile
import kotlinx.coroutines.launch

data class ProfileUiState(
    val profile: Profile? = null,
    val isEditing: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val editName: String = "",
    val editAge: String = "",
    val editCity: String = "",
    val editNationality: String = ""
)

class ProfileViewModel(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val addProfileUseCase: CreateProfileUseCase
) : ViewModel() {

    var uiState by mutableStateOf(ProfileUiState())
        private set

    fun loadProfile(userId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val profile = getProfileByIdUseCase(userId)

            if (profile == null) {
                val newProfile = Profile(
                    id = 0,
                    userId = userId,
                    email = "",
                    name = "Новый пользователь",
                    age = "",
                    city = "",
                    nationality = ""
                )
                val createdProfile = addProfileUseCase(newProfile)

                uiState = uiState.copy(
                    profile = createdProfile,
                    editName = createdProfile.name,
                    editAge = createdProfile.age,
                    editCity = createdProfile.city,
                    editNationality = createdProfile.nationality,
                    isLoading = false
                )
            } else {
                uiState = uiState.copy(
                    profile = profile,
                    editName = profile.name,
                    editAge = profile.age,
                    editCity = profile.city,
                    editNationality = profile.nationality,
                    isLoading = false
                )
            }
        }
    }

    fun onEditClick() {
        uiState = uiState.copy(isEditing = true)
    }

    fun onCancelClick() {
        uiState = uiState.copy(isEditing = false)
    }

    fun onApplyClick() {
        viewModelScope.launch {
            uiState.profile?.let { profile ->
                val updatedProfile = profile.copy(
                    name = uiState.editName,
                    age = uiState.editAge,
                    city = uiState.editCity,
                    nationality = uiState.editNationality
                )

                updateProfileUseCase(updatedProfile)

                uiState = uiState.copy(
                    profile = updatedProfile,
                    isEditing = false
                )
            }
        }
    }

    fun onNameChange(value: String) {
        uiState = uiState.copy(editName = value)
    }

    fun onAgeChange(value: String) {
        uiState = uiState.copy(editAge = value)
    }

    fun onCityChange(value: String) {
        uiState = uiState.copy(editCity = value)
    }

    fun onNationalityChange(value: String) {
        uiState = uiState.copy(editNationality = value)
    }
}



