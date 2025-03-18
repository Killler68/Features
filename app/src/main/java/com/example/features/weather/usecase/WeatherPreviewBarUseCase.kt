package com.example.features.weather.usecase

import com.example.features.weather.model.WeatherPreviewBar

class WeatherPreviewBarUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(city: String): WeatherPreviewBar =
        repository.weatherPreviewBar(city)
}