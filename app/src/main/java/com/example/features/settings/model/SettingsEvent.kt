package com.example.features.settings.model

sealed class SettingsEvent {
    data object DeleteUser : SettingsEvent()
    data object OnBack : SettingsEvent()
}