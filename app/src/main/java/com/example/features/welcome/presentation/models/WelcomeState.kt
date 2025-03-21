package com.example.features.welcome.presentation.models

import androidx.annotation.StringRes
import com.example.features.welcome.domain.entities.PagerItem

sealed class WelcomeState {
    data object Loading : WelcomeState()
    data class Success(val item: List<PagerItem>) : WelcomeState()
    data class Error(@StringRes val message: Int) : WelcomeState()
}