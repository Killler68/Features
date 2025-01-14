package com.example.features.weather.detailed.usecase

import com.example.features.weather.model.WeatherHoursDay

class WeatherHoursDayUseCase(
    private val repository: WeatherDetailedRepository
) {
    operator fun invoke(): List<WeatherHoursDay> = repository.getWeatherHoursDay()
}