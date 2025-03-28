package com.example.features.authorization.presentation.models

sealed class AuthorizationSideEffect {
    data class ToFeatures(val userId: Int) : AuthorizationSideEffect()
    data object ToRegistration : AuthorizationSideEffect()
    data class ErrorMessage(val error: Int) : AuthorizationSideEffect()
}