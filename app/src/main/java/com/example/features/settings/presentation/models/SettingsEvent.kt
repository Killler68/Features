package com.example.features.settings.presentation.models

sealed class SettingsEvent {
    data class DeleteUser(val userId: Int) : SettingsEvent()
    data class OnBack(val userId: Int) : SettingsEvent()
}