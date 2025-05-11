package com.example.features.weather.presentation.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.features.weather.presentation.models.WeatherEvent
import com.example.features.weather.presentation.models.WeatherState
import com.example.features.weather.presentation.WeatherViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun WeatherList(
    paddingValues: PaddingValues,
    state: WeatherState.Success,
) {
    val viewModel: WeatherViewModel = getViewModel()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        item {
            WeatherPreview(state.preview, state) //todo just pass preview and weather weeks here, and not the entire state
        }
        items(state.weatherWeek) { weatherData ->
            WeatherDaylyItem(
                weatherWeek = weatherData,
                state = state,
                onClick = { viewModel.dispatch(WeatherEvent.ToWeatherDetailed(weatherData.day)) }
            )
        }
    }
}