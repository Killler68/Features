package com.example.features.profile.presentation.models

sealed class ProfileSideEffect {
    data class NavigateTo(val route: String) : ProfileSideEffect()
}
