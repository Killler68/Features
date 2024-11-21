package com.example.features.weather.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.state.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class Event() {
    data object GoBack: Event()
    data class LoadWeather(val city: String?): Event()
    class NavigateTo(screen: String)
}

class WeatherViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val hoursWeatherUseCase: HoursWeatherUseCase,
    private val previewBarWeatherUseCase: PreviewBarWeatherUseCase
) : ViewModel() {

    private val _previewBarWeather =
        mutableStateOf(PreviewBarWeather("London", "", "", 0f, ""))
    val previewBarWeather: State<PreviewBarWeather> = _previewBarWeather

    private val _hoursWeather = mutableStateOf<List<HoursWeather>>(emptyList())
    val hoursWeather: State<List<HoursWeather>> = _hoursWeather

    private val _dailyWeather = mutableStateOf<List<DailyWeather>>(emptyList())
    val dailyWeather: State<List<DailyWeather>> = _dailyWeather

    private val _state: MutableStateFlow<WeatherState> = MutableStateFlow(WeatherState.Content)
    val state: StateFlow<WeatherState> get() = _state

    var isEnabled = mutableStateOf(false)


    fun loadWeather() {
        viewModelScope.launch {
            _state.value = WeatherState.Loading
            try {
                _dailyWeather.value = weatherUseCase()
                _hoursWeather.value = hoursWeatherUseCase()
                _previewBarWeather.value = previewBarWeatherUseCase()
                _state.value = WeatherState.Content
            } catch (e: Exception) {
                _state.value = WeatherState.Error(
                    title = e.message.toString(),
                    message = e.message.toString()
                )
            }
        }
    }

    private fun onError(title: String, message: String) {
        _state.value = WeatherState.Error(title, message)
    }
}