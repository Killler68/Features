package com.example.features.weather.model


data class WeatherData(
    val day: Long,
    val dtText: String,
    val temp: Double,
    val maxTemp: Double,
    val minTemp: Double,
    val icon: String,
    val partDay: String,
    val listWeek: List<WeatherWeek>
)

fun Map<String, List<WeatherWeek>>.toWeatherData() =
    this.map { (date, weatherList) ->
        WeatherData(
            day = weatherList.first().day,
            dtText = date,
            temp = weatherList.first().temp,
            maxTemp = weatherList.maxOf { it.temp },
            minTemp = weatherList.minOf { it.temp },
            icon = weatherList.first().icon,
            partDay = weatherList.first().partDay,
            listWeek = weatherList
        )
    }