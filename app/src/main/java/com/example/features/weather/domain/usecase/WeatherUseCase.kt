package com.example.features.weather.domain.usecase

import com.example.features.weather.domain.entities.WeatherWeek
import com.example.features.weather.domain.entities.toWeatherData

class WeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): List<WeatherWeek> {
        val weatherWeek: List<WeatherWeek> = repository.getWeatherWeek(city)

        if (weatherWeek.isEmpty()) return emptyList()

        val dailyWeather = weatherWeek
            .groupBy { it.dtText.substring(0, 10) }

        return dailyWeather.toWeatherData()
    }
}
