package com.example.features.weather.usecase

import android.content.Context
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather

interface WeatherRepository {

    fun getWeather(): List<DailyWeather>
    suspend fun getHoursWeather(context: Context): List<HoursWeather>
    fun previewBarWeather(): PreviewBarWeather
}
