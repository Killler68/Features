package com.example.features.weatherdetailed.domain.usecase

import com.example.features.weatherdetailed.domain.entities.WeatherHoursDay

class WeatherHoursDayUseCase(
    private val repository: WeatherDetailedRepository
) {
    suspend operator fun invoke(weatherId: Int, city: String): List<WeatherHoursDay> =
        repository.getWeatherHoursDay(weatherId, city).map { forecast ->
            forecast.copy(chanceRain = forecast.chanceRain * 100)
        }
}