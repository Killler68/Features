package com.example.features.weatherdetailed.domain.entities

import com.example.features.weather.domain.entities.WeatherDetailedDay

data class WeatherDetailsResult(
    val detailedDay: WeatherDetailedDay,
    val hoursDay: List<WeatherHoursDay>,
    val itemPager: List<ItemTemperature>
)