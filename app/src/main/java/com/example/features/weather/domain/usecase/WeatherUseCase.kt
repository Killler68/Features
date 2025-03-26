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
            .groupBy { it.dtText.substring(FIRST_DAY, LAST_DAY) }

        return dailyWeather.toWeatherData()
    }

    companion object {
        const val FIRST_DAY = 0
        const val LAST_DAY = 10
    }
}
