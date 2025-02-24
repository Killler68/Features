package com.example.features.weather.detailed.usecase

import com.example.features.weather.detailed.model.WeatherHoursDay

class WeatherHoursDayUseCase(
    private val repository: WeatherDetailedRepository
) {
    suspend operator fun invoke(weatherId: Int): List<WeatherHoursDay> = repository.getWeatherHoursDay(weatherId)
}