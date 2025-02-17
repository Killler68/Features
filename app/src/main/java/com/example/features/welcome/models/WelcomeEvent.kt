package com.example.features.welcome.models

sealed class WelcomeEvent {
    data object LoadPagerItem : WelcomeEvent()
    data object ToAuthorization : WelcomeEvent()
    data object ToRegistration : WelcomeEvent()
}