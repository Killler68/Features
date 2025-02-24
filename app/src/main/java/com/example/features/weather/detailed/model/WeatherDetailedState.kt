package com.example.features.weather.detailed.model

import com.example.features.weather.model.WeatherDetailedDay

sealed class WeatherDetailedState {

    data object Loading : WeatherDetailedState()
    data class Success(
        val weatherId: Int,
        val detailedDay: WeatherDetailedDay,
        val hoursDay: List<WeatherHoursDay>
    ) : WeatherDetailedState()

    data class Error(val message: String) : WeatherDetailedState()

}