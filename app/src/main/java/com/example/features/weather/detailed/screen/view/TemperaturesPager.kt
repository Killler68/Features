package com.example.features.weather.detailed.screen.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.common.extension.dateFormatDays
import com.example.features.common.extension.firstUppercaseString
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.weather.detailed.model.WeatherDetailedState
import com.example.features.common.view.PageIndicatorsView


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TemperaturesPager(state: WeatherDetailedState.Success) {

    val pagerState = rememberPagerState(pageCount = { state.itemPager.size })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(weatherColorExtension(state.detailedDay.partDay, ColorCategory.CARD))
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(pagerState) { page ->
            val item = state.itemPager[page]
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.date.dateFormatDays(),
                    fontSize = 16.sp,
                    color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT)
                )
                Text(
                    text = item.differenceText.firstUppercaseString(),
                    fontSize = 12.sp,
                    color = weatherColorExtension(state.detailedDay.partDay, ColorCategory.TEXT),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        PageIndicatorsView(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp),
            count = state.itemPager.size,
            currentPage = pagerState.currentPage,
            weatherColorExtension(state.detailedDay.partDay, ColorCategory.INDICATORS),
            Color.Gray
        )
    }
}