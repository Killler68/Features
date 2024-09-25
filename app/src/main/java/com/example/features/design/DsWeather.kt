package com.example.features.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.features.R
import com.example.features.ui.theme.Cyan

@Composable
fun DsWeather() {

    Column(
        Modifier
            .fillMaxSize()
    ) {
        DsWeatherActionBar()
        DsWeatherPreviewBar()
    }

}

@Composable
fun DsWeatherActionBar() {

    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Буденновск",
            modifier = Modifier
                .padding(start = 10.dp)

        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "image",
                modifier = Modifier
                    .size(50.dp)
            )
        }
    }
}

@Composable
fun DsWeatherPreviewBar() {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Cyan),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Сегодня, 26 сен. пн",
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = Color.White
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "image"
        )
        Text(
            text = "10 C",
            modifier = Modifier
                .padding(10.dp),
            fontSize = 30.sp,
            color = Color.White
        )
        Text(
            text = "Солнечно",
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = Color.White
        )
    }
}