package com.example.features.features.model

sealed class FeaturesSideEffect {

    data class NavigateTo(val route: String) : FeaturesSideEffect()
}