package com.example.features.weather.viewmodel

import com.example.features.weather.model.DailyWeather

interface WeatherUseCase {

    operator fun invoke(): List<DailyWeather>
}