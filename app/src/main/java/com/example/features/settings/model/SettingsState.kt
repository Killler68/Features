package com.example.features.settings.model

sealed class SettingsState {
    data object Loading : SettingsState()
    data object Success : SettingsState()
    data class Error(val message: String) : SettingsState()
}