package com.example.features.registration.presentation.models

sealed class RegistrationEvent {
    data class CreateUser(val login: String, val password: String) : RegistrationEvent()
    data object NavigateToAuthorization : RegistrationEvent()
}