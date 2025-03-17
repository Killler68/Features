package com.example.features.features.screen.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.features.features.model.FeaturesEvent
import com.example.features.features.model.FeaturesItemPager
import com.example.features.features.model.FeaturesState
import com.example.features.ui.theme.Cyan
import com.example.features.common.view.PageIndicatorsView


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeaturesPager(state: FeaturesState.Success, dispatch: (FeaturesEvent) -> Unit) {

    val pagerState = rememberPagerState(pageCount = { state.itemFeature.size })

    HorizontalPager(pagerState) { page ->

        Column {
            val items = state.itemFeature[page]

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.55f)
                    .padding(start = 60.dp, end = 60.dp, top = 50.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Cyan)
                    .clickable {
                        dispatch(FeaturesEvent.NavigateToFeature(items.feature))
                    },
            ) {

                when (page) {
                    FeaturesItemPager.NOTES.id -> {
                        NotesItemPager(state)
                    }

                    FeaturesItemPager.WEATHER.id -> {
                        WeatherItemPager(state)
                    }
                }
            }
        }
    }
    PageIndicatorsView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        count = state.itemFeature.size,
        currentPage = pagerState.currentPage,
        Cyan,
        Color.LightGray
    )
}