package com.example.features.weather.detailed.usecase

import com.example.features.weather.model.WeatherDetailedDay
import com.example.features.weather.detailed.model.WeatherHoursDay

interface WeatherDetailedRepository {

    suspend fun getWeatherDetailedDay(weatherId: Int, city: String): WeatherDetailedDay
    suspend fun getWeatherHoursDay(weatherId: Int, city: String): List<WeatherHoursDay>
}