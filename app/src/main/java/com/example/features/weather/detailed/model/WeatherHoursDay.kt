package com.example.features.weather.detailed.model

data class WeatherHoursDay(
    val hours: Long,
    val icon: String,
    val temp: Double,
    val chanceRain: Double
)