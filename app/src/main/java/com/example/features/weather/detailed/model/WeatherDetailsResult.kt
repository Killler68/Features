package com.example.features.weather.detailed.model

import com.example.features.weather.model.WeatherDetailedDay

data class WeatherDetailsResult(
    val detailedDay: WeatherDetailedDay,
    val hoursDay: List<WeatherHoursDay>,
    val itemPager: List<ItemTemperature>
)