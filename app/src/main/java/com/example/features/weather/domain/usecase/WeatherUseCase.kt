package com.example.features.weather.domain.usecase

import com.example.features.weather.domain.entities.WeatherData
import com.example.features.weather.domain.entities.WeatherWeek
import com.example.features.weather.domain.entities.toWeatherData

class WeatherUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(city: String): List<WeatherData> {
        val weatherWeek: List<WeatherWeek> = repository.getWeatherWeek(city)

        if (weatherWeek.isEmpty()) return emptyList()

        val weatherDays =
            weatherWeek.groupBy { it.dtText.substring(0, 10) }
                .mapValues { (_, data) ->
                    data.sortedBy { it.day }
                }

        return weatherDays.toWeatherData()
    }
}
