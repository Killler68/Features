package com.example.features.settings.model

sealed class SettingsSideEffect {

    data class NavigateTo(val route: String) : SettingsSideEffect()
}