package com.example.features.weather.usecase

import com.example.features.weather.model.WeatherData
import com.example.features.weather.model.WeatherWeek
import com.example.features.weather.model.toWeatherData

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
