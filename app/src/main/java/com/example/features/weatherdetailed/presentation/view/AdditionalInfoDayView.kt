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
import com.example.features.common.extension.weatherVisibilityExtension
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState

@Composable
fun AdditionalInfoDayView(state: WeatherDetailedState.Success) {

    val visibility = weatherVisibilityExtension(state.detailedDay.visibility)
    val humidity = state.detailedDay.humidity
    val wind = state.detailedDay.windSpeed
    val pressure = state.detailedDay.pressure

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
            subInfo = visibility,
            image = R.drawable.visibility,
            state = state
        )
        CardAdditionalInfoDay(
            width = 1f,
            alignment = Alignment.CenterEnd,
            startPadding = 5.dp,
            endPadding = 10.dp,
            info = stringResource(R.string.humidity),
            subInfo = stringResource(R.string.percent, humidity),
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
            subInfo = stringResource(R.string.wind_unit, wind),
            image = R.drawable.wind,
            state = state
        )
        CardAdditionalInfoDay(
            width = 1f,
            alignment = Alignment.CenterEnd,
            startPadding = 5.dp,
            endPadding = 10.dp,
            info = stringResource(R.string.pressure),
            subInfo = stringResource(R.string.pressure_unit, pressure),
            image = R.drawable.barometer,
            state = state
        )
    }
}