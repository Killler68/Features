package com.example.features.weather.detailed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.strings.city
import com.example.features.navigation.Screens
import com.example.features.weather.detailed.model.WeatherDetailedEvent
import com.example.features.weather.detailed.model.WeatherDetailedSideEffect
import com.example.features.weather.detailed.model.WeatherDetailedState
import com.example.features.weather.detailed.usecase.ItemTemperatureUseCase
import com.example.features.weather.detailed.usecase.WeatherDetailedDayUseCase
import com.example.features.weather.detailed.usecase.WeatherHoursDayUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val ONE_DAY_MILLIS = 86_400_000L

class WeatherDetailedViewModel(
    private val weatherDetailedDayUseCase: WeatherDetailedDayUseCase,
    private val weatherHoursDayUseCase: WeatherHoursDayUseCase,
    private val itemTemperature: ItemTemperatureUseCase
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
                val todayDt = weatherId.toLong() * 1000
                val yesterdayDt = todayDt - ONE_DAY_MILLIS
                val tomorrowDt = todayDt + ONE_DAY_MILLIS

                val detailedDay = weatherDetailedDayUseCase(weatherId, city)

                val yesterdayWeather = runCatching {
                    weatherDetailedDayUseCase((yesterdayDt / 1000).toInt(), city)
                }.getOrNull()

                val tomorrowWeather = runCatching {
                    weatherDetailedDayUseCase((tomorrowDt / 1000).toInt(), city)
                }.getOrNull()

                val itemPager = itemTemperature(
                    todayWeather = detailedDay,
                    yesterdayWeather = yesterdayWeather,
                    tomorrowWeather = tomorrowWeather
                )

                _state.value = WeatherDetailedState.Success(
                    weatherId = weatherId,
                    detailedDay = detailedDay,
                    hoursDay = weatherHoursDayUseCase(weatherId, city = city),
                    itemPager = itemPager
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