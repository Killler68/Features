package com.example.features.weather.usecase

import com.example.features.common.extension.dateFormatUnixTime
import com.example.features.weather.model.WeatherData
import com.example.features.weather.model.WeatherWeek
import com.example.features.weather.model.toWeatherData
import com.example.features.weather.viewmodel.WeatherUseCase
import kotlinx.coroutines.coroutineScope
class WeatherUseCaseImpl(
    private val repository: WeatherRepository
) : WeatherUseCase {

    private val weatherDays = mutableMapOf<String, MutableList<WeatherWeek>>()

    override suspend fun invoke(): List<WeatherData> = coroutineScope {
        repository.getWeatherWeek().forEach {
            if (weatherDays[it.day.dateFormatUnixTime()] != null)
                weatherDays[it.day.dateFormatUnixTime()]?.add(it)
            else weatherDays[it.day.dateFormatUnixTime()] = mutableListOf(it)
        }
        val weatherDataWithOutLastElement = weatherDays.toWeatherData().toMutableList()
        weatherDataWithOutLastElement.removeLast()
        weatherDataWithOutLastElement
    }
}