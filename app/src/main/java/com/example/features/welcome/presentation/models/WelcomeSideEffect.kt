package com.example.features.welcome.presentation.models

sealed class WelcomeSideEffect {
    data class NavigateTo(val router: String) : WelcomeSideEffect()
}