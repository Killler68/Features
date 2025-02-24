package com.example.features.weather.detailed.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.features.common.extension.extensionConditionWeather
import com.example.features.common.extension.extensionTemperatureWeather
import com.example.features.common.extension.getRawNameWeatherExtension
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.detailed.model.WeatherDetailedState

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TextPreview(
    temp: Double,
    description: String,
    maxTemp: Double,
    minTemp: Double,
    feelingTemp: Double,
    state: WeatherDetailedState.Success
) {

    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
        ) {
            Text(
                text = "${temp.toInt()}°",
                fontSize = 50.sp,
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 20.dp),
                color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
            )
            Text(
                text = description.getRawNameWeatherExtension(),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp, bottom = 20.dp),
                color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
            )
            Text(
                text = "${maxTemp.toInt()}° / ${minTemp.toInt()}° Ощущается как ${feelingTemp.toInt()}°",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
            )
        }

        Column {

            GlideImage(
                model = extensionConditionWeather(description),
                contentDescription = "animated_gif_weather",
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .height(50.dp)
                    .width(100.dp),
                contentScale = ContentScale.Inside
            )
            GlideImage(
                model = extensionTemperatureWeather(temp),
                contentDescription = "animated_gif_children",
                modifier = Modifier
                    .height(150.dp)
                    .width(100.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}