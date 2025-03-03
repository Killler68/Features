package com.example.features.weather.detailed.usecase

import com.example.features.weather.model.WeatherDetailedDay

class WeatherDetailedDayUseCase(private val repository: WeatherDetailedRepository) {

    suspend operator fun invoke(weatherId: Int, city: String): WeatherDetailedDay {
        val forecasts = repository.getWeatherHoursDay(weatherId, city) // Получаем все прогнозы за день

        if (forecasts.isEmpty()) {
            throw Exception("Данные за день не найдены")
        }

        val minTemp = forecasts.minOf { it.temp }
        val maxTemp = forecasts.maxOf { it.temp }

        val detailedWeather = repository.getWeatherDetailedDay(weatherId, city)

        return detailedWeather.copy(
            minTemp = minTemp,
            maxTemp = maxTemp
        )
    }
}