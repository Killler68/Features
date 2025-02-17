package com.example.features.welcome.models

sealed class WelcomeSideEffect {
    data object ToAuthorization : WelcomeSideEffect()
    data object ToRegistration : WelcomeSideEffect()
}