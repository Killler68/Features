package com.example.features.weather.model

data class WeatherDetailedDay(
    val city: String,
    val dtText: Long,
    val temp: Double,
    val description: String,
    val maxTemp: Double,
    val minTemp: Double,
    val feelingTemp: Double,
    val pressure: Int,
    val visibility: Int,
    val humidity: Int,
    val windDirection: Int,
    val windSpeed: Double,
    val probabilityPrecipitation: Double,
    val partDay: String,
    val sunSet: Long,
    val sunRise: Long
)