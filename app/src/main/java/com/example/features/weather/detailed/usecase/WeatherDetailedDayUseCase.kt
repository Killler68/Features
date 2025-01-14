package com.example.features.weather.detailed.usecase

import com.example.features.weather.model.WeatherDetailedDay

class WeatherDetailedDayUseCase(private val repository: WeatherDetailedRepository) {

    operator fun invoke(): WeatherDetailedDay = repository.getWeatherDetailedDay()
}