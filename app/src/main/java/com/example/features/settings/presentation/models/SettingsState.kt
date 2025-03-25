package com.example.features.settings.presentation.models

sealed class SettingsState {
    data object Loading : SettingsState()
    data object Success : SettingsState()
    data class Error(val message: Int) : SettingsState()
}