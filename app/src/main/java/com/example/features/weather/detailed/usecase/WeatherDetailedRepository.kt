package com.example.features.weather.detailed.usecase

import com.example.features.weather.model.WeatherAdditionalInfoDay
import com.example.features.weather.model.WeatherDetailedDay
import com.example.features.weather.model.WeatherHoursDay

interface WeatherDetailedRepository {

    suspend fun getWeatherDetailedDay(): WeatherDetailedDay
    suspend fun getWeatherHoursDay(): List<WeatherHoursDay>
    suspend fun getWeatherAdditionalInfoDay(): WeatherAdditionalInfoDay
}