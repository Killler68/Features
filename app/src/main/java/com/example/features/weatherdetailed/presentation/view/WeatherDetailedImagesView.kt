package com.example.features.weatherdetailed.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.features.R
import com.example.features.common.extension.extensionConditionWeather
import com.example.features.common.extension.extensionTemperatureWeather
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherDetailedImagesView(state: WeatherDetailedState.Success) {
    val imageCondition =
        state.hoursDay.first().icon.extensionConditionWeather(state.detailedDay.partDay)
    val imageTemperature = extensionTemperatureWeather(state.detailedDay.temp)

    Column {
        GlideImage(
            model = imageCondition,
            contentDescription = stringResource(R.string.state_weather),
            modifier = Modifier
                .padding(bottom = 5.dp)
                .height(50.dp)
                .width(100.dp),
            contentScale = ContentScale.Inside
        )
        GlideImage(
            model = imageTemperature,
            contentDescription = stringResource(R.string.children),
            modifier = Modifier
                .height(150.dp)
                .width(100.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}