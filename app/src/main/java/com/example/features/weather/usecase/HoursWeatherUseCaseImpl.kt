package com.example.features.weather.usecase

import android.content.Context
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.viewmodel.HoursWeatherUseCase

class HoursWeatherUseCaseImpl(
    private val repository: WeatherRepository
) : HoursWeatherUseCase {

    override suspend fun invoke(
        context: Context
    ): List<HoursWeather> = repository.getHoursWeather(context)
}