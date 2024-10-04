package com.example.features.weather.viewmodel

import androidx.lifecycle.ViewModel
import com.example.features.weather.model.DailyWeather

class WeatherViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val hoursWeatherUseCase: HoursWeatherUseCase,
    private val previewBarWeatherUseCase: PreviewBarWeatherUseCase
) : ViewModel() {

    fun getWeather(): List<DailyWeather> = weatherUseCase()
    fun getHoursWeather() = hoursWeatherUseCase()
    fun getPreviewBarWeather() = previewBarWeatherUseCase()
}