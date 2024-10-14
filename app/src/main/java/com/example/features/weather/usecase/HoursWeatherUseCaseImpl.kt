package com.example.features.weather.usecase

import com.example.features.weather.model.HoursWeather
import com.example.features.weather.viewmodel.HoursWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HoursWeatherUseCaseImpl(
    private val repository: WeatherRepository
) : HoursWeatherUseCase {

    override suspend fun invoke(): List<HoursWeather> = withContext(Dispatchers.IO) {
        repository.getHoursWeather()
    }
}