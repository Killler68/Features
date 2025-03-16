package com.example.features.profile.model

sealed class ProfileSideEffect {
    data class NavigateTo(val route: String) : ProfileSideEffect()
}
