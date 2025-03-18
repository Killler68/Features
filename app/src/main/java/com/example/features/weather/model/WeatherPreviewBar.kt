package com.example.features.weather.model

data class WeatherPreviewBar(
    var city: String = "London",
    val date: Long,
    val dtText: String,
    val icon: String,
    val temp: Double,
    val description: String,
    val partDay: String
)
