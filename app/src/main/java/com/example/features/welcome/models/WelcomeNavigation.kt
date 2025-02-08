package com.example.features.welcome.models

sealed class WelcomeNavigation {
    data object ToAuthorization : WelcomeNavigation()
    data object ToRegistration : WelcomeNavigation()
}