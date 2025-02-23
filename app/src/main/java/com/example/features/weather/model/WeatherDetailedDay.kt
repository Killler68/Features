package com.example.features.weather.model

data class WeatherDetailedDay(
    val city: String,
    val temp: Double,
    val descriptionWeather: String,
    val maxTemp: Double,
    val minTemp: Double,
    val feelingTemp: Double,
)

val emptyWeatherDetailedDay =
    WeatherDetailedDay("",0.0, "" , 0.0,0.0,0.0)
