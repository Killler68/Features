package com.example.features.authorization.presentation.models

sealed class AuthorizationState {
    data object Loading : AuthorizationState()
    data object Success : AuthorizationState()
    data class Error(val message: Int) : AuthorizationState()
}