package com.example.features.weather.domain.usecase

import com.example.features.weather.domain.entities.WeatherWeek
import com.example.features.weather.domain.entities.WeatherPreview

interface WeatherRepository {

    suspend fun getWeatherWeek(city: String): List<WeatherWeek>
    suspend fun getWeatherPreview(city: String): WeatherPreview
}
