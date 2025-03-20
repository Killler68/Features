package com.example.features.weatherdetailed.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.features.common.extension.weatherColorExtension
import com.example.features.common.utils.ColorCategory
import com.example.features.common.view.PageIndicatorsView
import com.example.features.weatherdetailed.presentation.models.WeatherDetailedState


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
            val itemTemperature = state.itemPager[page]
            TemperaturesPagerItem(itemTemperature, state)
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
