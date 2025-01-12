package com.example.features.welcome.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.welcome.models.PagerItems


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomePager(items: List<PagerItems>) {

    val pagerState = rememberPagerState(pageCount = { items.size })

    HorizontalPager(
        state = pagerState
    ) { page ->
        Column {
            Image(
                painter = painterResource(items[page].image),
                contentDescription = "image $page",
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 5.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            )
            Text(
                text = items[page].title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 40.dp, vertical = 5.dp)
            )
            Text(
                text = items[page].subTitle,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
            )
        }
    }
    PageIndicators(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        count = items.size,
        currentPage = pagerState.currentPage
    )
}