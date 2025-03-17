package com.example.features.features.screen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.features.common.view.ShimmerEffect
import com.example.features.features.model.FeaturesState


@Composable
fun ItemPagerWeather(state: FeaturesState.Success) {

    if (state.isWeatherLoading) ShimmerEffect()
    else {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            ItemPagerWeatherView(state)
        }
    }
}
