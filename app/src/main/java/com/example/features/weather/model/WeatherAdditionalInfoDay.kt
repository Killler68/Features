package com.example.features.weather.model

data class WeatherAdditionalInfoDay(
    val temp: Double,
    val description: String,
    val uvIndex: Float,
    val humidity: Int,
    val wind: Float,
    val pressure: Float
)

val emptyWeatherAdditionalInfoDay =
    WeatherAdditionalInfoDay(0.0, "", 0.0f, 0, 0.0f, 0.0f)
