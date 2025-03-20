package com.example.features.weather.domain.entities

fun Map<String, List<WeatherWeek>>.toWeatherData() =
    this.map { (date, weatherList) ->
        WeatherWeek(
            day = weatherList.first().day,
            dtText = date,
            temp = weatherList.first().temp,
            maxTemp = weatherList.maxOf { it.temp },
            minTemp = weatherList.minOf { it.temp },
            icon = weatherList.first().icon,
            partDay = weatherList.first().partDay,
            hourlyList = weatherList
        )
    }
