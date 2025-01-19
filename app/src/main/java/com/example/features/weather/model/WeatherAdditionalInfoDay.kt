package com.example.features.weather.model

data class WeatherAdditionalInfoDay(
    val temp: Float,
    val description: String
//    val nextDay: String,
//    val descriptionTemp: String,
//    val temp: String,
//
//    val descriptionWeather: String,
//
//    val sunrise: String,
//
//    val uvIndex: String,
//    val humidity: String,
//    val wind: String,
//    val pressure: String
)

val emptyWeatherAdditionalInfoDay =
    WeatherAdditionalInfoDay(0.0f, "")
