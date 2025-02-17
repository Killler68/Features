package com.example.features.weather.model

sealed class WeatherSideEffect {

    data class NavigateTo(val route: String) : WeatherSideEffect()
}