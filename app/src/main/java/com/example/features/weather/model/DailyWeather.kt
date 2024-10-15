package com.example.features.weather.model

data class DailyWeather(
    val dayOfWeek: String,
    val maxTemp: Float,
    val minTemp: Float,
    val icon: String
)
