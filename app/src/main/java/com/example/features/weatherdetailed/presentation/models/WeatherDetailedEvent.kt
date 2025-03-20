package com.example.features.weatherdetailed.presentation.models

sealed class WeatherDetailedEvent {
    data class LoadData(val weatherId: Int) : WeatherDetailedEvent()
    data object ToBack : WeatherDetailedEvent()
}