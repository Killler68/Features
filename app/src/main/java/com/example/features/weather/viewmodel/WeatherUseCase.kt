package com.example.features.weather.viewmodel

import com.example.features.weather.model.WeatherData

interface WeatherUseCase {

    suspend operator fun invoke(city: String): List<WeatherData>
}