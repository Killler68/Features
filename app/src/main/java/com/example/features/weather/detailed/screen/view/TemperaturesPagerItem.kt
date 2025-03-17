package com.example.features.weather.detailed.screen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.dateFormatDays
import com.example.features.common.extension.firstUppercaseString
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.detailed.model.ItemTemperature
import com.example.features.weather.detailed.model.WeatherDetailedState


@Composable
fun TemperaturesPagerItem(item: ItemTemperature, state: WeatherDetailedState.Success) {

    val textColor = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
    val differenceText = item.differenceText.firstUppercaseString()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.date.dateFormatDays(),
            fontSize = 16.sp,
            color = textColor
        )
        Text(
            text = differenceText,
            fontSize = 12.sp,
            color = textColor,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}