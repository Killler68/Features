package com.example.features.weather.usecase

import android.content.Context
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.model.WeatherResponse

interface WeatherRepository {

    suspend fun getDailyWeather(): List<DailyWeather>
    suspend fun getHoursWeather(context: Context): List<HoursWeather>
    suspend fun previewBarWeather(): PreviewBarWeather
}
