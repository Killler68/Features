package com.example.features.weather.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.features.navigation.Screens
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.state.WeatherEvent
import com.example.features.weather.state.WeatherState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val hoursWeatherUseCase: HoursWeatherUseCase,
    private val previewBarWeatherUseCase: PreviewBarWeatherUseCase,
    private val navController: NavController
) : ViewModel() {

    private val _weather = mutableStateOf<List<DailyWeather>>(emptyList())
    val weather: State<List<DailyWeather>> = _weather

    private val _hoursWeather = mutableStateOf<List<HoursWeather>>(emptyList())
    val hoursWeather: State<List<HoursWeather>> = _hoursWeather

    private val _previewBarWeather = mutableStateOf(PreviewBarWeather(0, "", 0, "", ""))
    val previewBarWeather: State<PreviewBarWeather> = _previewBarWeather

    private val _state: MutableStateFlow<WeatherState> = MutableStateFlow(WeatherState.Content)
    val state: StateFlow<WeatherState> get() = _state

    init {
        loadWeather()
    }

    private fun onError(title: String, message: String) {
        _state.value = WeatherState.Error(title, message)
    }

    fun dispatch(event: WeatherEvent) {
        when (event) {
            WeatherEvent.OnBackClick -> onBack()
        }
    }

    private fun loadWeather() {
        viewModelScope.launch {
            _state.value = WeatherState.Loading
            delay(1000L)
            try {
                _weather.value = weatherUseCase()
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

    private fun onBack() {
        navController.navigate(Screens.FeaturesFragment.route)
    }

}