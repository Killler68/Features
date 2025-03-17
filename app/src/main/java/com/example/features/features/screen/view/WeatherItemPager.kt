package com.example.features.features.screen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.features.common.extension.extensionTemperatureWeather
import com.example.features.common.extension.getRawNameFeaturesCityEngToRuExtension
import com.example.features.common.extension.imageWeatherExtension
import com.example.features.features.model.FeaturesState
import com.example.features.common.view.ShimmerEffect


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherItemPager(state: FeaturesState.Success) {

    if (state.isWeatherLoading) {
        ShimmerEffect()
    } else {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            if (state.itemWeather != null) {
                val iconRes =
                    remember(
                        state.itemWeather.icon,
                        state.itemWeather.date
                    ) {
                        state.itemWeather.icon.imageWeatherExtension(state.itemWeather.date)
                    }

                Image(
                    painter = painterResource(iconRes),
                    contentDescription = "condition_weather",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(48.dp)
                )

                GlideImage(
                    model = extensionTemperatureWeather(
                        state.itemWeather.temp
                    ),
                    contentDescription = "condition_weather",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                        .size(width = 100.dp, height = 160.dp)
                )

                Text(
                    text = "В ${state.itemWeather.city.getRawNameFeaturesCityEngToRuExtension()} сегодня ",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )

                Text(
                    text = "${state.itemWeather.temp.toInt()}°",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )
            } else NotFound("Погода не обнаружена")
        }
    }
}