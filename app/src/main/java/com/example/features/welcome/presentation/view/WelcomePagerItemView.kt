package com.example.features.welcome.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.welcome.domain.entities.PagerItems


@Composable
fun WelcomePagerItemView(items: List<PagerItems>, page: Int) {
    Column {
        Image(
            painter = painterResource(items[page].image),
            contentDescription = "item $page",
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