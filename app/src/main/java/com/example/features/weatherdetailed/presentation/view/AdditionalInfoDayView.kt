package com.example.features.weatherdetailed.presentation.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.features.R
import com.example.features.common.extension.weatherVisibilityExtension
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState

@Composable
fun AdditionalInfoDayView(state: WeatherDetailedState.Success) {
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
            info = "Видимость",
            subInfo = weatherVisibilityExtension(state.detailedDay.visibility),
            image = R.drawable.visibility,
            state = state
        )
        CardAdditionalInfoDay(
            width = 1f,
            alignment = Alignment.CenterEnd,
            startPadding = 5.dp,
            endPadding = 10.dp,
            info = "Влажность",
            subInfo = "${state.detailedDay.humidity}%",
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
            info = "Ветер",
            subInfo = "${state.detailedDay.windSpeed} м/с",
            image = R.drawable.wind,
            state = state
        )
        CardAdditionalInfoDay(
            width = 1f,
            alignment = Alignment.CenterEnd,
            startPadding = 5.dp,
            endPadding = 10.dp,
            info = "Давление",
            subInfo = "${state.detailedDay.pressure} мбар",
            image = R.drawable.barometer,
            state = state
        )
    }
}