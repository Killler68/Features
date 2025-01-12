package com.example.features.weather.state

sealed class WeatherState {

    data object Loading : WeatherState()
    data object Content : WeatherState()
    data class Error(val message: String): WeatherState()

}