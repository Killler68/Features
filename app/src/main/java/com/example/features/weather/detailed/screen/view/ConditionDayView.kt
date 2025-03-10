package com.example.features.weather.detailed.screen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.dateFormatHours
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.common.utils.SemiCircularProgress
import com.example.features.weather.detailed.model.WeatherDetailedState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ConditionDayView(state: WeatherDetailedState.Success) {
    val today = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) }
    val selectedDate = remember {
        SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ).format(Date(state.detailedDay.dt * 1000))
    }
    val isToday = today == selectedDate

    val currentTime = remember { System.currentTimeMillis() / 1000 }
    val sunrise = state.detailedDay.sunRise
    val sunset = state.detailedDay.sunSet

    val progress = if (isToday) {
        when {
            currentTime <= sunrise -> 0f
            currentTime >= sunset -> 1f
            else -> (currentTime - sunrise).toFloat() / (sunset - sunrise).toFloat()
        }
    } else {
        0f
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(weatherColorExtension(state.detailedDay.partDay, ColorCategory.CARD))
    ) {
        SemiCircularProgress(
            progress = progress,
            modifier = Modifier.padding(10.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Box(contentAlignment = Alignment.CenterStart) {
                Text(
                    "Восход",
                    fontSize = 18.sp,
                    color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
                )
            }
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Закат",
                    fontSize = 18.sp,
                    color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Box(contentAlignment = Alignment.CenterStart) {
                Text(
                    state.detailedDay.sunRise.dateFormatHours(),
                    fontSize = 18.sp,
                    color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
                )
            }
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    state.detailedDay.sunSet.dateFormatHours(),
                    fontSize = 18.sp,
                    color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
                )
            }
        }
    }
}