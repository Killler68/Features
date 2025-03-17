package com.example.features.weather.detailed.screen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.detailed.model.WeatherDetailedState
import com.example.features.weather.detailed.model.WeatherHoursDay


@Composable
fun WeatherDetailedRainDrop(state: WeatherDetailedState.Success, hoursDay: WeatherHoursDay) {
    Row {
        Image(
            painter = painterResource(R.drawable.rain_drop),
            contentDescription = "rain_drop",
            colorFilter = ColorFilter.tint(
                weatherColorExtension(state.detailedDay.partDay, ColorCategory.IMAGE)
            ),
            modifier = Modifier
                .padding(start = 3.dp, end = 3.dp, top = 5.dp, bottom = 10.dp)
                .size(8.dp),
        )

        Text(
            text = "${(hoursDay.chanceRain * 100).toInt()}%",
            fontSize = 10.sp,
            color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
        )
    }
}