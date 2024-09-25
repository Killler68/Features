package com.example.features.weather.model

data class DailyWeather(
    val id: Int,
    val dayOfWeek: String,
    val temp: String,
    val icon: Int
)
