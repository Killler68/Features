package com.example.features.weather.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.features.common.extension.firstUppercaseString
import com.example.features.common.extension.getRawNameCityEngToRuExtension
import com.example.features.common.strings.city
import com.example.features.weather.presentation.models.WeatherEvent
import com.example.features.weather.presentation.models.WeatherState
import com.example.features.weather.presentation.WeatherViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun IsChangeLocationView(isDialogVisible: Boolean, state: WeatherState.Success) {

    val viewModel: WeatherViewModel = getViewModel()
    var editCity by remember { mutableStateOf(state.preview.city) }

    if (isDialogVisible) {
        ChangeLocationDialog(
            title = editCity.getRawNameCityEngToRuExtension(),
            onCityChange = { editCity = it },
            onDismiss = { viewModel.dispatch(WeatherEvent.OnCloseShowDialogChangeCity) },
            state = state,
            onSave = {
                city = editCity.trim().firstUppercaseString()
                viewModel.loadWeather(city)
                viewModel.dispatch(WeatherEvent.OnCloseShowDialogChangeCity)
            }
        )
    }
}