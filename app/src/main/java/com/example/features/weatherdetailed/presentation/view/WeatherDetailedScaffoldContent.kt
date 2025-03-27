package com.example.features.weatherdetailed.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.features.R
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.common.view.BottomNameView
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState


@Composable
fun WeatherDetailedScaffoldContent(
    paddingValues: PaddingValues,
    state: WeatherDetailedState.Success
) {
    val textColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        WeatherDetailedPreview(state = state)

        WeatherDetailedInfoDay(state = state)

        TemperaturesPager(state = state)

        WeatherDetailedAdditionalInfo(state = state)

        SunConditionView(state = state)

        BottomNameView(
            name = stringResource(R.string.open_weather_map),
            textColor = textColor
        )
    }
}