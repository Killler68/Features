package com.example.features.authorization.model

sealed class AuthorizationSideEffect {
    data class ToFeatures(val userId: Int) : AuthorizationSideEffect()
    data object ToRegistration : AuthorizationSideEffect()
}