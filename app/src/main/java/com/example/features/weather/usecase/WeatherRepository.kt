package com.example.features.weather.usecase

import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather

interface WeatherRepository {

    fun getWeather(): List<DailyWeather>
    fun getHoursWeather(): List<HoursWeather>
    fun previewBarWeather(): PreviewBarWeather
}
