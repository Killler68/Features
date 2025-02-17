package com.example.features.registration.model

sealed class RegistrationState {
    data object Loading : RegistrationState()
    data object Success : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}