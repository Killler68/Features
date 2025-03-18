package com.example.features.welcome.models

sealed class WelcomeSideEffect {
    data class NavigateTo(val router: String) : WelcomeSideEffect()
}