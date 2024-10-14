package com.example.features.weather.viewmodel

import com.example.features.weather.model.HoursWeather

interface HoursWeatherUseCase {

    suspend operator fun invoke(): List<HoursWeather>
}