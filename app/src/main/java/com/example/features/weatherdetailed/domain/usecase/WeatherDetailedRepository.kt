package com.example.features.weatherdetailed.domain.usecase

import com.example.features.weatherdetailed.domain.entities.WeatherHoursDay
import com.example.features.weather.domain.entities.WeatherDetailedDay

interface WeatherDetailedRepository {

    suspend fun getWeatherDetailedDay(weatherId: Int, city: String): WeatherDetailedDay
    suspend fun getWeatherHoursDay(weatherId: Int, city: String): List<WeatherHoursDay>
}