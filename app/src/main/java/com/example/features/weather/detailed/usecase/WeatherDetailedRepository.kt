package com.example.features.weather.detailed.usecase

import com.example.features.weather.model.WeatherAdditionalInfoDay
import com.example.features.weather.model.WeatherDetailedDay
import com.example.features.weather.model.WeatherHoursDay

interface WeatherDetailedRepository {

    fun getWeatherDetailedDay(): WeatherDetailedDay
    fun getWeatherHoursDay(): List<WeatherHoursDay>
    fun getWeatherAdditionalInfoDay(): WeatherAdditionalInfoDay
}