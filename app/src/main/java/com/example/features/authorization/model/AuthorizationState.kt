package com.example.features.authorization.model

sealed class AuthorizationState {
    data object Loading : AuthorizationState()
    data object Success : AuthorizationState()
    data class Error(val message: String) : AuthorizationState()
}