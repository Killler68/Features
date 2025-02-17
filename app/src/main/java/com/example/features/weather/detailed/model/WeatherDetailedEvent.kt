package com.example.features.weather.detailed.model

sealed class WeatherDetailedEvent {
    data class LoadData(val weatherId: Int) : WeatherDetailedEvent()
    data object ToBack : WeatherDetailedEvent()
}