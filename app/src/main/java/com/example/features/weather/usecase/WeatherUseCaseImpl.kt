package com.example.features.weather.usecase

import com.example.features.weather.model.DailyWeather
import com.example.features.weather.viewmodel.WeatherUseCase

class WeatherUseCaseImpl(private val repository: WeatherRepository) : WeatherUseCase {
    override fun invoke(): List<DailyWeather> = repository.getWeather()
}