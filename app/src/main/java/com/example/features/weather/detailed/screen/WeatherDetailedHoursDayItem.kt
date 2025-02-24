package com.example.features.weather.detailed.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.features.R
import com.example.features.common.extension.dateFormatHours
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.ui.theme.LightGray
import com.example.features.weather.detailed.model.WeatherDetailedState
import com.example.features.weather.detailed.model.WeatherHoursDay

@Composable
fun WeatherDetailedHoursDayItem(hoursDay: WeatherHoursDay, state: WeatherDetailedState.Success) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 3.dp, top = 5.dp, bottom = 5.dp)
            .background(weatherColorExtension(state.detailedDay.partDay, ColorCategory.CARD))
            .border(BorderStroke(width = 1.dp, Color.White), RoundedCornerShape(12.dp))
            .padding(horizontal = 5.dp)
    ) {
        Text(
            text = hoursDay.hours.dateFormatHours(),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(vertical = 5.dp),
            color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
        )
        Image(
            painter = rememberAsyncImagePainter("https:" + hoursDay.icon),
            contentDescription = "hours_weather",
            modifier = Modifier
                .size(28.dp)
        )
        Text(
            text = "${hoursDay.temp.toInt()}°",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp),
            color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
        )

        Row {
            Image(
                painter = painterResource(R.drawable.rain_drop),
                contentDescription = "rain_drop",
                modifier = Modifier
                    .padding(start = 3.dp, end = 3.dp, top = 5.dp, bottom = 10.dp)
                    .size(8.dp),
                colorFilter = ColorFilter.tint(LightGray)
            )

            Text(
                text = "${hoursDay.chanceRain}%",
                fontSize = 10.sp,
                color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
            )
        }
    }
}