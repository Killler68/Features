package com.example.features.weatherdetailed.presentation.models

import com.example.features.weatherdetailed.domain.entities.ItemTemperature
import com.example.features.weatherdetailed.domain.entities.WeatherHoursDay
import com.example.features.weather.domain.entities.WeatherDetailedDay

sealed class WeatherDetailedState {

    data object Loading : WeatherDetailedState()
    data class Success(
        val weatherId: Int,
        val detailedDay: WeatherDetailedDay,
        val hoursDay: List<WeatherHoursDay>,
        val itemPager: List<ItemTemperature>
    ) : WeatherDetailedState()

    data class Error(val message: Int) : WeatherDetailedState()

}