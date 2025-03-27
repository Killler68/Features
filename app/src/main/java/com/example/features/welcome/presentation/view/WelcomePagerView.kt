package com.example.features.welcome.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.features.common.view.PageIndicatorsView
import com.example.features.ui.theme.Cyan
import com.example.features.welcome.domain.entities.PagerItem


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomePagerView(item: List<PagerItem>) {

    val pagerState = rememberPagerState(pageCount = { item.size })

    HorizontalPager(
        state = pagerState
    ) { page ->
        WelcomePagerItemView(item[page])
    }
    PageIndicatorsView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        count = item.size,
        currentPage = pagerState.currentPage,
        activeColor = Cyan,
        inactiveColor = Color.LightGray
    )
}