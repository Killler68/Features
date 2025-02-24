package com.example.features.weather.detailed.usecase

import com.example.features.weather.model.WeatherDetailedDay

class WeatherDetailedDayUseCase(private val repository: WeatherDetailedRepository) {

    suspend operator fun invoke(weatherId: Int, city: String): WeatherDetailedDay =
        repository.getWeatherDetailedDay(weatherId, city)
}