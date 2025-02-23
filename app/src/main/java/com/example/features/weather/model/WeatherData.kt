package com.example.features.weather.model


data class WeatherData(
    val day: Long,
    val temp: Double,
    val maxTemp: Double,
    val minTemp: Double,
    val icon: String,
    val listWeek: List<WeatherWeek>
)
fun Map<String, List<WeatherWeek>>.toWeatherData() =
    this.map {
        WeatherData(
            it.value.first().day,
            it.value.first().temp,
            it.value.first().maxTemp,
            it.value.first().minTemp,
            it.value.first().icon,
            it.value
        )
    }