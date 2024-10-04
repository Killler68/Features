package com.example.features.weather.viewmodel

import com.example.features.weather.model.PreviewBarWeather

interface PreviewBarWeatherUseCase {

operator fun invoke(): PreviewBarWeather
}