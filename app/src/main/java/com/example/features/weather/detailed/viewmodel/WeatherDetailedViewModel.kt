package com.example.features.weather.detailed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.navigation.Screens
import com.example.features.weather.detailed.model.WeatherDetailedEvent
import com.example.features.weather.detailed.model.WeatherDetailedSideEffect
import com.example.features.weather.detailed.model.WeatherDetailedState
import com.example.features.weather.detailed.usecase.WeatherDetailedDayUseCase
import com.example.features.weather.detailed.usecase.WeatherHoursDayUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherDetailedViewModel(
    private val weatherDetailedDayUseCase: WeatherDetailedDayUseCase,
    private val weatherHoursDayUseCase: WeatherHoursDayUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<WeatherDetailedState>(WeatherDetailedState.Loading)
    val state: StateFlow<WeatherDetailedState> get() = _state.asStateFlow()

    private val _effect = MutableSharedFlow<WeatherDetailedSideEffect>()
    val effect: SharedFlow<WeatherDetailedSideEffect> get() = _effect.asSharedFlow()

    fun dispatch(event: WeatherDetailedEvent) {
        when (event) {
            is WeatherDetailedEvent.LoadData -> loadData(event.weatherId)
            is WeatherDetailedEvent.ToBack -> toBack(Screens.Weather.route)
        }
    }

    private fun loadData(weatherId: Int) {
        viewModelScope.launch {
            try {
                val detailedDay = weatherDetailedDayUseCase(weatherId)
                val hoursDay = weatherHoursDayUseCase(weatherId)
                _state.value = WeatherDetailedState.Success(
                    weatherId = weatherId,
                    detailedDay = detailedDay,
                    hoursDay = hoursDay
                )
            } catch (e: Exception) {
                _state.value = WeatherDetailedState.Error(e.localizedMessage ?: "Ошибка")
            }
        }
    }

    private fun toBack(route: String) {
        viewModelScope.launch {
            _effect.emit(WeatherDetailedSideEffect.ToBack(route))
        }
    }
}