package com.example.features.registration.model

sealed class RegistrationSideEffect {
    data class NavigateTo(val route: String) : RegistrationSideEffect()
}