package com.example.features.settings.presentation.models

sealed class SettingsSideEffect {

    data class NavigateTo(val route: String) : SettingsSideEffect()
    data class ErrorMessage(val message: Int) : SettingsSideEffect()
}