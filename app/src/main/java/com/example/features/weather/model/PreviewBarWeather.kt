package com.example.features.weather.model

data class PreviewBarWeather(
    var city: String = "London",
    val date: Long,
    val icon: String,
    val temp: Double,
    val description: String
)
