package com.example.features.weather.usecase

import com.example.features.weather.model.WeatherWeek
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather

interface WeatherRepository {

    suspend fun getWeatherWeek(city: String): List<WeatherWeek>
    suspend fun previewBarWeather(city: String): PreviewBarWeather
}
