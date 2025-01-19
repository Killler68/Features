package com.example.features.weather.detailed.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.weather.detailed.usecase.WeatherAdditionalInfoDayUseCase
import com.example.features.weather.detailed.usecase.WeatherDetailedDayUseCase
import com.example.features.weather.detailed.usecase.WeatherHoursDayUseCase
import com.example.features.weather.model.WeatherAdditionalInfoDay
import com.example.features.weather.model.WeatherDetailedDay
import com.example.features.weather.model.WeatherHoursDay
import com.example.features.weather.model.emptyWeatherAdditionalInfoDay
import com.example.features.weather.model.emptyWeatherDetailedDay
import kotlinx.coroutines.launch

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

    fun loadWeatherDetailedDay(weatherId: Int) {
        viewModelScope.launch {
            _detailedDay.value = weatherDetailedDayUseCase(weatherId)

        }
    }

    fun loadWeatherAdditionalInfoDay(weatherId: Int) {
        viewModelScope.launch {
            _additionalInfoDay.value = weatherAdditionalInfoDayUseCase(weatherId)

        }
    }

    fun loadWeatherHoursDay(weatherId: Int) {
        viewModelScope.launch {
            _hoursDay.value = weatherHoursDayUseCase(weatherId)

        }
    }
}