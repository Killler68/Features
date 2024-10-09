package com.example.features.weather.viewmodel

import android.content.Context
import com.example.features.weather.model.HoursWeather

interface HoursWeatherUseCase {

    suspend operator fun invoke(context: Context): List<HoursWeather>
}