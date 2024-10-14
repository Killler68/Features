package com.example.features.weather.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val previewBarWeatherUseCase: PreviewBarWeatherUseCase
) : ViewModel() {

    private val _hoursWeather = mutableStateOf<List<HoursWeather>>(emptyList())
    val hoursWeather: State<List<HoursWeather>> = _hoursWeather

    private val _previewBarWeather =
        mutableStateOf<PreviewBarWeather>(PreviewBarWeather("", "", 0f, ""))
    val previewBarWeather: State<PreviewBarWeather> = _previewBarWeather


    //    private val _previewBarWeather = mutableStateOf(WeatherResponse(0, "", 0, "", ""))
//    val previewBarWeather: State<PreviewBarWeather> = _previewBarWeather

    private val _state: MutableStateFlow<WeatherState> = MutableStateFlow(WeatherState.Content)
    val state: StateFlow<WeatherState> get() = _state
//
//    init {
//        loadWeather()
//    }


    fun loadWeather(context: Context) {
        viewModelScope.launch {
            _state.value = WeatherState.Loading
            try {

                _dailyWeather.value = weatherUseCase()
                _hoursWeather.value = hoursWeatherUseCase(context)
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

    fun dispatch(event: WeatherEvent) {
        when (event) {
            WeatherEvent.OnBackClick -> onBack()
        }
    }

    private val _dailyWeather = mutableStateOf<List<DailyWeather>>(emptyList())
    val dailyWeather: State<List<DailyWeather>> = _dailyWeather
//

    private fun onBack() {
//        navController.navigate(Screens.Features.route)
    }

}