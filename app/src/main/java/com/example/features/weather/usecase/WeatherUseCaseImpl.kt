package com.example.features.weather.usecase

import com.example.features.weather.model.DailyWeather
import com.example.features.weather.viewmodel.WeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherUseCaseImpl(private val repository: WeatherRepository) : WeatherUseCase {
    override suspend fun invoke(): List<DailyWeather> = withContext(Dispatchers.IO) {
        repository.getDailyWeather()
    }
}