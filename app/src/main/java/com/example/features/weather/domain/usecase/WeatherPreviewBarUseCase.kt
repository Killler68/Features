package com.example.features.weather.domain.usecase

import com.example.features.weather.domain.entities.WeatherPreviewBar

class WeatherPreviewBarUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(city: String): WeatherPreviewBar =
        repository.weatherPreviewBar(city)
}