package com.example.features.weather.model

sealed class WeatherEvent {

    data object ToBack : WeatherEvent()
    data class ToWeatherDetailed(val weatherId: Int) : WeatherEvent()
    data object LoadData : WeatherEvent()
}