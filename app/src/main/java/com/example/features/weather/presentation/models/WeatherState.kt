package com.example.features.weather.presentation.models

import com.example.features.weather.domain.entities.WeatherPreview
import com.example.features.weather.domain.entities.WeatherWeek

sealed class WeatherState {

    data object Loading : WeatherState()
    data class Success(
        val weatherWeek: List<WeatherWeek>,
        val preview: WeatherPreview
    ) : WeatherState()

    data class Error(val message: Int) : WeatherState()
}