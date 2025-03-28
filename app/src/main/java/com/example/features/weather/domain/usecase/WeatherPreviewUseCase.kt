package com.example.features.weather.domain.usecase

import com.example.features.weather.domain.entities.WeatherPreview

class WeatherPreviewUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(city: String): WeatherPreview =
        repository.getWeatherPreview(city)
}