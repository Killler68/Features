package com.example.features.weather.model

data class PreviewBarWeather(
    var city: String = "London",
    val date: String,
    val icon: String,
    val temp: Float,
    val description: String
)
