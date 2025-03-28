package com.example.features.features.presentation.models

sealed class FeaturesSideEffect {

    data class NavigateTo(val route: String) : FeaturesSideEffect()
    data class NavigateToFeature(val userId: Int, val route: String) : FeaturesSideEffect()
}