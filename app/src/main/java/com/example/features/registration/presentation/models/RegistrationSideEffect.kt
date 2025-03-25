package com.example.features.registration.presentation.models

sealed class RegistrationSideEffect {
    data class NavigateTo(val route: String) : RegistrationSideEffect()
    data class ErrorMessage(val message: Int) : RegistrationSideEffect()
}