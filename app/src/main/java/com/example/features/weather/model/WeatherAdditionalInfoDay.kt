package com.example.features.weather.model

data class WeatherAdditionalInfoDay(
    val temp: Float,
    val description: String,
//    val nextDay: String,
//    val descriptionTemp: String,
//    val temp: String,
//
//    val descriptionWeather: String,
//
//    val sunrise: String,
//
    val uvIndex: Float,
    val humidity: Int,
    val wind: Float,
    val pressure: Float
)

val emptyWeatherAdditionalInfoDay =
    WeatherAdditionalInfoDay(0.0f, "", 0.0f, 0, 0.0f, 0.0f)
