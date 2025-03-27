package com.example.features.weatherdetailed.domain.usecase

import android.content.Context
import com.example.features.common.strings.city
import com.example.features.weather.domain.entities.WeatherDetailedDay
import com.example.features.weatherdetailed.domain.entities.WeatherDetailsResult

class WeatherDetailedUseCase(
    private val repository: WeatherDetailedRepository,
    private val itemTemperature: TemperatureItemUseCase,
    private val weatherHoursDayUseCase: WeatherHoursDayUseCase,
    private val context: Context
) {

    suspend fun getWeatherDetails(weatherId: Int): WeatherDetailsResult {
        val todayDt = weatherId.toLong() * MILLIS_IN_SECOND
        val yesterdayDt = todayDt - ONE_DAY_MILLIS
        val tomorrowDt = todayDt + ONE_DAY_MILLIS

        val detailedDay = getWeatherForDay(weatherId)
        val yesterdayWeather = getWeatherOrNull((yesterdayDt / MILLIS_IN_SECOND).toInt())
        val tomorrowWeather = getWeatherOrNull((tomorrowDt / MILLIS_IN_SECOND).toInt())

        return WeatherDetailsResult(
            detailedDay = detailedDay,
            hoursDay = weatherHoursDayUseCase(weatherId, city),
            itemPager = itemTemperature(
                todayWeather = detailedDay,
                yesterdayWeather = yesterdayWeather,
                tomorrowWeather = tomorrowWeather,
                context = context
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

    companion object {
        private const val ONE_DAY_MILLIS = 86_400_000L
        private const val MILLIS_IN_SECOND = 1000
    }
}