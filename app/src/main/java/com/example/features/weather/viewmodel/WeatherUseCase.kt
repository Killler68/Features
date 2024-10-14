package com.example.features.weather.viewmodel

import com.example.features.weather.model.DailyWeather

interface WeatherUseCase {

   suspend operator fun invoke(): List<DailyWeather>
}