package com.example.features.profile.model

sealed class ProfileState {

    data object Loading : ProfileState()
    data object Success : ProfileState()
    data class Error(val message: String) : ProfileState()
}