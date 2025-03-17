package com.example.features.registration.model

sealed class RegistrationEvent {
    data class CreateUser(val login: String, val password: String) : RegistrationEvent()
    data object NavigateToAuthorization : RegistrationEvent()
}