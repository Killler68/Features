package com.example.features.weather.detailed.screen.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.features.weather.detailed.model.WeatherDetailedState


@Composable
fun WeatherDetailedListHours(state: WeatherDetailedState.Success) {
    LazyRow(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        itemsIndexed(
            state.hoursDay
        ) { _, item ->
            WeatherDetailedHoursDayItem(hoursDay = item, state)
        }
    }
}