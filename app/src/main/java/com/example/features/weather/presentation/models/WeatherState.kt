package com.example.features.weather.presentation.models

import com.example.features.weather.domain.entities.WeatherData
import com.example.features.weather.domain.entities.WeatherPreviewBar

sealed class WeatherState {

    data object Loading : WeatherState()
    data class Success(
        val weatherWeek: List<WeatherData>,
        val preview: WeatherPreviewBar
    ) : WeatherState()

    data class Error(val message: String) : WeatherState()
}