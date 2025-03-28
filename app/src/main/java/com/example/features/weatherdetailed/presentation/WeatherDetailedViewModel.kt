package com.example.features.weatherdetailed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.R
import com.example.features.common.navigation.Screens
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedEvent
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedSideEffect
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState
import com.example.features.weatherdetailed.domain.usecase.WeatherDetailedDayUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class WeatherDetailedViewModel(
    private val weatherDetailedDayUseCase: WeatherDetailedDayUseCase
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
                val result = weatherDetailedDayUseCase.getWeatherDetails(weatherId)
                _state.value = WeatherDetailedState.Success(
                    weatherId = weatherId,
                    detailedDay = result.detailedDay,
                    hoursDay = result.hoursDay,
                    temperatureItem = result.itemPager
                )
            } catch (e: Exception) {
                _state.value = WeatherDetailedState.Error(R.string.error_load)
            }
        }
    }

    private fun toBack(route: String) {
        viewModelScope.launch {
            _effect.emit(WeatherDetailedSideEffect.ToBack(route))
        }
    }
}