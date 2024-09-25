package com.example.features.weather.model

data class HourlyWeather(
    val id: Int,
    val time: String,
    val icon: Int,
    val temp: String
)
