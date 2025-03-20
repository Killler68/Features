package com.example.features.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.navigation.Screens
import com.example.features.common.strings.city
import com.example.features.weather.presentation.models.WeatherEvent
import com.example.features.weather.presentation.models.WeatherSideEffect
import com.example.features.weather.presentation.models.WeatherState
import com.example.features.weather.domain.usecase.WeatherPreviewBarUseCase
import com.example.features.weather.domain.usecase.WeatherUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val weatherPreviewBarUseCase: WeatherPreviewBarUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val state: StateFlow<WeatherState> get() = _state.asStateFlow()

    private val _effect = MutableSharedFlow<WeatherSideEffect>()
    val effect: SharedFlow<WeatherSideEffect> get() = _effect.asSharedFlow()

    init {
        loadWeather(city)
    }

    fun dispatch(event: WeatherEvent) {
        when (event) {
            WeatherEvent.LoadData -> loadWeather(city)
            WeatherEvent.OnShowDialogChangeCity -> showDialog()
            WeatherEvent.OnCloseShowDialogChangeCity -> clearSideEffects()
            is WeatherEvent.ToBack -> toBack()
            is WeatherEvent.ToWeatherDetailed -> navigateToDetailed(event.weatherId)
        }
    }

    fun loadWeather(city: String) {
        viewModelScope.launch {
            _state.value = WeatherState.Loading
            try {
                val weatherWeek = weatherUseCase(city)
                val previewWeather = weatherPreviewBarUseCase(city)
                _state.value = WeatherState.Success(
                    weatherWeek = weatherWeek,
                    preview = previewWeather,
                )
            } catch (e: Exception) {
                _state.value = WeatherState.Error(e.localizedMessage ?: "Ошибка загрузки данных")
            }
        }
    }

    private fun navigateToDetailed(weatherId: Long) {
        viewModelScope.launch {
            _effect.emit(
                WeatherSideEffect.NavigateTo(
                    Screens.WeatherDetailedScreen.createRouter(
                        weatherId.toString()
                    )
                )
            )
        }
    }

    private fun navigateTo(route: String) {
        viewModelScope.launch {
            _effect.emit(WeatherSideEffect.NavigateTo(route))
        }
    }

    private fun toBack() {
        viewModelScope.launch {
            _effect.emit(WeatherSideEffect.ToBack)
        }
    }

    private fun showDialog() {
        viewModelScope.launch {
            _effect.emit(WeatherSideEffect.Popup)
        }
    }

    private fun clearSideEffects() {
        viewModelScope.launch {
            _effect.emit(WeatherSideEffect.None)
        }
    }
}