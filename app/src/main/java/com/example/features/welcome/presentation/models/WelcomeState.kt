package com.example.features.welcome.presentation.models

import com.example.features.welcome.domain.entities.PagerItems

sealed class WelcomeState {
    data object Loading : WelcomeState()
    data class Success(val items: List<PagerItems>) : WelcomeState()
    data class Error(val message: String) : WelcomeState()
}