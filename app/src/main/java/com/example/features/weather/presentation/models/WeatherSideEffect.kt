package com.example.features.weather.presentation.models

sealed class WeatherSideEffect {

    data class NavigateTo(val route: String) : WeatherSideEffect()
    data object ToBack : WeatherSideEffect()
    data object Popup : WeatherSideEffect()
    data object None : WeatherSideEffect()
}