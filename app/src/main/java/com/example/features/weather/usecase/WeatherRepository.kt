package com.example.features.weather.usecase

import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather

interface WeatherRepository {

    suspend fun getDailyWeather(): List<DailyWeather>
    suspend fun getHoursWeather(): List<HoursWeather>
    suspend fun previewBarWeather(): PreviewBarWeather
}
