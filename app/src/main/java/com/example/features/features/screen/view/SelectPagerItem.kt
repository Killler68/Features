package com.example.features.features.screen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.features.features.model.FeaturesItemPager
import com.example.features.features.model.FeaturesState
import com.example.features.ui.theme.Cyan

@Composable
fun SelectPagerItem(itemPage: Int, onClick: () -> Unit, state: FeaturesState.Success) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.55f)
            .padding(start = 60.dp, end = 60.dp, top = 50.dp, bottom = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Cyan)
            .clickable { onClick() },
    ) {
        when (itemPage) {
            FeaturesItemPager.NOTES.id -> {
                ItemPagerNotes(state)
            }

            FeaturesItemPager.WEATHER.id -> {
                ItemPagerWeather(state)
            }
        }
    }
}