package com.example.features.weather.usecase

import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.viewmodel.PreviewBarWeatherUseCase

class PreviewBarWeatherUseCaseImpl(private val repository: WeatherRepository) :
    PreviewBarWeatherUseCase {

    override fun invoke(): PreviewBarWeather = repository.previewBarWeather()
}