package com.example.features.weather.screen.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.features.weather.model.WeatherEvent
import com.example.features.weather.model.WeatherState
import com.example.features.weather.viewmodel.WeatherViewModel

@Composable
fun WeatherList(
    paddingValues: PaddingValues,
    state: WeatherState.Success,
    viewModel: WeatherViewModel
) {

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        item {
            WeatherPreviewBar(state.preview, state)
        }
        items(state.weatherWeek) { weatherData ->
            DailyWeatherItem(
                weatherWeek = weatherData,
                state = state
            ) {
                viewModel.dispatch(WeatherEvent.ToWeatherDetailed(weatherData.day))
            }
        }
    }
}