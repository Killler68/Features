package com.example.features.weatherdetailed.domain.usecase

import com.example.features.common.strings.city
import com.example.features.weatherdetailed.domain.entities.WeatherDetailsResult
import com.example.features.weather.domain.entities.WeatherDetailedDay


private const val ONE_DAY_MILLIS = 86_400_000L

class WeatherDetailedUseCase(
    private val repository: WeatherDetailedRepository,
    private val itemTemperature: ItemTemperatureUseCase,
    private val weatherHoursDayUseCase: WeatherHoursDayUseCase
) {

    suspend fun getWeatherDetails(weatherId: Int): WeatherDetailsResult {
        val todayDt = weatherId.toLong() * 1000
        val yesterdayDt = todayDt - ONE_DAY_MILLIS
        val tomorrowDt = todayDt + ONE_DAY_MILLIS

        val detailedDay = getWeatherForDay(weatherId)
        val yesterdayWeather = getWeatherOrNull((yesterdayDt / 1000).toInt())
        val tomorrowWeather = getWeatherOrNull((tomorrowDt / 1000).toInt())

        return WeatherDetailsResult(
            detailedDay = detailedDay,
            hoursDay = weatherHoursDayUseCase(weatherId, city),
            itemPager = itemTemperature(
                todayWeather = detailedDay,
                yesterdayWeather = yesterdayWeather,
                tomorrowWeather = tomorrowWeather
            )
        )
    }

    private suspend fun getWeatherForDay(weatherId: Int): WeatherDetailedDay {
        val forecasts = repository.getWeatherHoursDay(weatherId, city)
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

    private suspend fun getWeatherOrNull(weatherId: Int): WeatherDetailedDay? {
        return runCatching { getWeatherForDay(weatherId) }.getOrNull()
    }
}