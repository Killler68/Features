package com.example.features.weather.viewmodel

import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.model.WeatherResponse

interface PreviewBarWeatherUseCase {

suspend operator fun invoke(): PreviewBarWeather
}