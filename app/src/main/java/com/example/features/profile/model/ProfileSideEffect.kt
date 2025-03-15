package com.example.features.profile.model

sealed class ProfileSideEffect {

    data object None : ProfileSideEffect()
    data object OpenSettings : ProfileSideEffect()
    data class NavigateTo(val route: String) : ProfileSideEffect()
}