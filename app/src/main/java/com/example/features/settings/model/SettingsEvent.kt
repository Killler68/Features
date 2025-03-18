package com.example.features.settings.model

sealed class SettingsEvent {
    data class DeleteUser(val userId: Int) : SettingsEvent()
    data class OnBack(val userId: Int) : SettingsEvent()
}