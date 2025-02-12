package com.example.features.weather.detailed.model

sealed class WeatherDetailedSideEffect {

    data class ToBack(val route: String) : WeatherDetailedSideEffect()
}