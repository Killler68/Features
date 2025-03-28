package com.example.features.authorization.presentation.models

sealed class AuthorizationEvent {
    data class User(val login: String, val password: String) : AuthorizationEvent()
    data object NavigateToRegistration : AuthorizationEvent()
}