package com.example.features.weather.model

data class WeatherWeek(
    val day: Long,
    val dtText: String,
    val temp: Double,
    val maxTemp: Double,
    val minTemp: Double,
    val icon: String,
    val partDay: String
)
