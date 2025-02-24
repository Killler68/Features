package com.example.features.weather.detailed.model

data class WeatherHoursData(
    val hours: Long,
    val icon: String,
    val temp: Double,
    val chanceRain: Double,
    val listHoursDay: List<WeatherHoursDay>
)

fun Map<String, List<WeatherHoursDay>>.toWeatherHoursData() =
    this.map {
        WeatherHoursData(
            it.value.first().hours,
            it.value.first().icon,
            it.value.first().temp,
            it.value.first().chanceRain,
            it.value
        )
    }
