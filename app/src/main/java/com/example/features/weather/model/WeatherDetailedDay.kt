package com.example.features.weather.model

data class WeatherDetailedDay(
    val id: Int,
    val temp: String,
    val descriptionWeather: String,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val feelingTemp: String,
)

val emptyWeatherDetailedDay =
    WeatherDetailedDay(0,"", "", "", "", "", "")
