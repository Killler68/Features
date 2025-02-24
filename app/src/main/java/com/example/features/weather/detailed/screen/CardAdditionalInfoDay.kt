package com.example.features.weather.detailed.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.detailed.model.WeatherDetailedState

@Composable
fun CardAdditionalInfoDay(
    width: Float,
    alignment: Alignment,
    startPadding: Dp,
    endPadding: Dp,
    info: String,
    subInfo: String,
    state: WeatherDetailedState.Success
) {

    Box(
        modifier = Modifier
            .fillMaxWidth(width),
        contentAlignment = alignment
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = startPadding, end = endPadding)
                .size(width = 1.dp, 100.dp),
            colors = CardDefaults.cardColors(
                containerColor = weatherColorExtension(
                    state.detailedDay.partDay,
                    ColorCategory.CARD
                )
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = info,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
                )
                Text(
                    text = subInfo,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
                )
            }
        }
    }
}