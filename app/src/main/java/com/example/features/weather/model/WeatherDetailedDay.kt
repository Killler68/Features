package com.example.features.weather.model

data class WeatherDetailedDay(
    val city: String,
    val temp: Float,
    val descriptionWeather: String,
    val maxTemp: Float,
    val minTemp: Float,
    val feelingTemp: Float,
)

val emptyWeatherDetailedDay =
    WeatherDetailedDay("",0.0f, "" , 0.0f,0.0f,0.0f)
