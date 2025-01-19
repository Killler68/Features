package com.example.features.weather.model

data class WeatherDetailedDay(
//    val id: Int,
    val temp: Float,
    val descriptionWeather: String,
    val maxTemp: Float,
    val minTemp: Float,
    val feelingTemp: Int,
)

val emptyWeatherDetailedDay =
    WeatherDetailedDay(0.0f, "" , 0.0f,0.0f,0)
