package com.example.features.weather.detailed.usecase

import com.example.features.weather.model.WeatherAdditionalInfoDay

class WeatherAdditionalInfoDayUseCase(private val repository: WeatherDetailedRepository) {

    suspend operator fun invoke(weatherId: Int): WeatherAdditionalInfoDay =
        repository.getWeatherAdditionalInfoDay(weatherId)
}