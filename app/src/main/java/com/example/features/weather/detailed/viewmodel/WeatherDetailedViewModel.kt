package com.example.features.weather.detailed.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.features.weather.detailed.usecase.WeatherAdditionalInfoDayUseCase
import com.example.features.weather.detailed.usecase.WeatherDetailedDayUseCase
import com.example.features.weather.detailed.usecase.WeatherHoursDayUseCase
import com.example.features.weather.model.WeatherAdditionalInfoDay
import com.example.features.weather.model.WeatherDetailedDay
import com.example.features.weather.model.WeatherHoursDay
import com.example.features.weather.model.emptyWeatherAdditionalInfoDay
import com.example.features.weather.model.emptyWeatherDetailedDay

class WeatherDetailedViewModel(
    private val weatherDetailedDayUseCase: WeatherDetailedDayUseCase,
    private val weatherAdditionalInfoDayUseCase: WeatherAdditionalInfoDayUseCase,
    private val weatherHoursDayUseCase: WeatherHoursDayUseCase
) : ViewModel() {


    private val _detailedDay = mutableStateOf(emptyWeatherDetailedDay)
    val detailedDay: State<WeatherDetailedDay> = _detailedDay

    private val _additionalInfoDay = mutableStateOf(emptyWeatherAdditionalInfoDay)
    val additionalInfoDay: State<WeatherAdditionalInfoDay> = _additionalInfoDay

    private val _hoursDay = mutableStateOf<List<WeatherHoursDay>>(emptyList())
    val hoursDay: State<List<WeatherHoursDay>> = _hoursDay

    fun loadWeatherDetailedDay() {
        _detailedDay.value = weatherDetailedDayUseCase()
    }

    fun loadWeatherAdditionalInfoDay() {
        _additionalInfoDay.value = weatherAdditionalInfoDayUseCase()
    }

    fun loadWeatherHoursDay() {
        _hoursDay.value = weatherHoursDayUseCase()
    }
}