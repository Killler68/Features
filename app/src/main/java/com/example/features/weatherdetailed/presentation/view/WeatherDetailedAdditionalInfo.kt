package com.example.features.weatherdetailed.presentation.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.features.R
import com.example.features.common.extension.weatherFormatVisibility
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState

@Composable
fun WeatherDetailedAdditionalInfo(state: WeatherDetailedState.Success) {

    val visibilityText = weatherFormatVisibility(state.detailedDay.visibility)
    val humidityText = state.detailedDay.humidity
    val windText = state.detailedDay.windSpeed
    val pressureText = state.detailedDay.pressure

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        CardAdditionalInfoDay(
            width = 0.5f,
            alignment = Alignment.CenterStart,
            startPadding = 10.dp,
            endPadding = 5.dp,
            info = stringResource(R.string.visibility),
            subInfo = visibilityText,
            image = R.drawable.visibility,
            state = state
        )
        CardAdditionalInfoDay(
            width = 1f,
            alignment = Alignment.CenterEnd,
            startPadding = 5.dp,
            endPadding = 10.dp,
            info = stringResource(R.string.humidity),
            subInfo = stringResource(R.string.percent, humidityText),
            image = R.drawable.humidity,
            state = state
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        CardAdditionalInfoDay(
            width = 0.5f,
            alignment = Alignment.CenterStart,
            startPadding = 10.dp,
            endPadding = 5.dp,
            info = stringResource(R.string.wind),
            subInfo = stringResource(R.string.wind_unit, windText),
            image = R.drawable.wind,
            state = state
        )
        CardAdditionalInfoDay(
            width = 1f,
            alignment = Alignment.CenterEnd,
            startPadding = 5.dp,
            endPadding = 10.dp,
            info = stringResource(R.string.pressure),
            subInfo = stringResource(R.string.pressure_unit, pressureText),
            image = R.drawable.barometer,
            state = state
        )
    }
}