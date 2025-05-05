package com.example.features.features.presentation.models

sealed class FeaturesSideEffect {

    data class NavigateTo(val route: String) : FeaturesSideEffect()
}