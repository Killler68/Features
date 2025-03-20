package com.example.features.features.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.features.common.view.PageIndicatorsView
import com.example.features.features.presentation.models.FeaturesEvent
import com.example.features.features.presentation.models.FeaturesState
import com.example.features.ui.theme.Cyan


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeaturesPager(state: FeaturesState.Success, dispatch: (FeaturesEvent) -> Unit) {

    val pagerState = rememberPagerState(pageCount = { state.itemFeature.size })

    HorizontalPager(pagerState) { page ->

        Column {
            val items = state.itemFeature[page]
            SelectPagerItem(
                itemPage = page,
                onClick = { dispatch(FeaturesEvent.NavigateToFeature(items.feature)) },
                state = state
            )
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