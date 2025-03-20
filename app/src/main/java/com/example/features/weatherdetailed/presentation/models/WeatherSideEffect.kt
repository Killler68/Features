package com.example.features.weatherdetailed.presentation.models

sealed class WeatherDetailedSideEffect {

    data class ToBack(val route: String) : WeatherDetailedSideEffect()
}