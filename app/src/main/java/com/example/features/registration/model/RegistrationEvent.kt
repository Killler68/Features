package com.example.features.registration.model

sealed class RegistrationEvent {
    data object CreateUser : RegistrationEvent()
    data object NavigateToAuthorization : RegistrationEvent()
}