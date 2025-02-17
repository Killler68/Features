package com.example.features.weather.model

sealed class WeatherState {

    data object Loading : WeatherState()
    data class Success(
        val dayly: List<DailyWeather>,
        val hours: List<HoursWeather>,
        val preview: PreviewBarWeather
    ) : WeatherState()

    data class Error(val message: String) : WeatherState()
}