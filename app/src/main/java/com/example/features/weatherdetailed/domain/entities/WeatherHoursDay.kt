package com.example.features.weatherdetailed.domain.entities

data class WeatherHoursDay(
    val hours: Long,
    val icon: String,
    val temp: Double,
    val chanceRain: Double
)