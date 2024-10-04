package com.example.features.weather.usecase

import com.example.features.weather.model.HoursWeather
import com.example.features.weather.viewmodel.HoursWeatherUseCase

class HoursWeatherUseCaseImpl(
    private val repository: WeatherRepository
) : HoursWeatherUseCase {

    override fun invoke(): List<HoursWeather> = repository.getHoursWeather()
}