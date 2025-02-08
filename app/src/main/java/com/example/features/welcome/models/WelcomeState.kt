package com.example.features.welcome.models

sealed class WelcomeState {
    data object Loading : WelcomeState()
    data class Success(val items: List<PagerItems>) : WelcomeState()
    data class Error(val message: String) : WelcomeState()
}