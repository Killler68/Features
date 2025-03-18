package com.example.features.weather.model

sealed class WeatherState {

    data object Loading : WeatherState()
    data class Success(
        val weatherWeek: List<WeatherData>,
        val preview: WeatherPreviewBar
    ) : WeatherState()

    data class Error(val message: String) : WeatherState()
}