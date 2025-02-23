package com.example.features.weather.model

data class WeatherDetailedDay(
    val city: String,
    val temp: Double,
    val description: String,
    val maxTemp: Double,
    val minTemp: Double,
    val feelingTemp: Double,
    val pressure: Int,
    val humidity: Int,
    val windDirection: Int,
    val windSpeed: Double,
    val probabilityPrecipitation: Double,
    val partDay: String,
)

//val emptyWeatherDetailedDay =
//    WeatherDetailedDay("",0.0, "" , 0.0,0.0,0.0)
