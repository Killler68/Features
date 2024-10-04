package com.example.features.weather.viewmodel

import com.example.features.weather.model.HoursWeather

interface HoursWeatherUseCase {

    operator fun invoke(): List<HoursWeather>
}