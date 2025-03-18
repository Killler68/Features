package com.example.features.weather.usecase

import com.example.features.weather.model.WeatherWeek
import com.example.features.weather.model.WeatherPreviewBar

interface WeatherRepository {

    suspend fun getWeatherWeek(city: String): List<WeatherWeek>
    suspend fun weatherPreviewBar(city: String): WeatherPreviewBar
}
