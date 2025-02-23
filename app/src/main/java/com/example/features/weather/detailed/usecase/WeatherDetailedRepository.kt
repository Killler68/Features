package com.example.features.weather.detailed.usecase

import com.example.features.weather.model.WeatherDetailedDay
import com.example.features.weather.model.WeatherHoursDay

interface WeatherDetailedRepository {

    suspend fun getWeatherDetailedDay(weatherId: Int): WeatherDetailedDay
    suspend fun getWeatherHoursDay(weatherId: Int): List<WeatherHoursDay>
}