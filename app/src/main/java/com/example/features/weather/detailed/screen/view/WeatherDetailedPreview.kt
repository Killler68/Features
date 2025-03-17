package com.example.features.weather.detailed.screen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.firstUppercaseString
import com.example.features.common.extension.getRawNameWeatherExtension
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.detailed.model.WeatherDetailedState

@Composable
fun WeatherDetailedPreview(state: WeatherDetailedState.Success) {

    val textColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
    val temp = state.detailedDay.temp.toInt()
    val minTemp = state.detailedDay.minTemp.toInt()
    val maxTemp = state.detailedDay.maxTemp.toInt()
    val feelingTemp = state.detailedDay.feelingTemp.toInt()
    val description =
        state.detailedDay.description.firstUppercaseString().getRawNameWeatherExtension()

    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
        ) {
            Text(
                text = "${temp}°",
                fontSize = 50.sp,
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 20.dp),
                color = textColor
            )
            Text(
                text = description,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp, bottom = 20.dp),
                color = textColor
            )
            Text(
                text = "${minTemp}° / ${maxTemp}° Ощущается как ${feelingTemp}°",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                color = textColor
            )
        }
        WeatherDetailedImagesView(state)
    }
}
