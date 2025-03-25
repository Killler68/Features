package com.example.features.registration.presentation.models

sealed class RegistrationState {
    data object Loading : RegistrationState()
    data object Success : RegistrationState()
    data class Error(val message: Int) : RegistrationState()
}