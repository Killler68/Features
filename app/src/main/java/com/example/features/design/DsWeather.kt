package com.example.features.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HourlyWeather

@Composable
fun DsWeather() {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DsWeatherActionBar()
        DsWeatherPreviewBar()
        DsDailyWeatherPanel()
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

@Composable
fun DsDailyWeatherPanel() {

    LazyColumn(
        Modifier
    ) {
        itemsIndexed(
            listOf(
                DailyWeather(0, "пн", "12 C", 1),
                DailyWeather(0, "пн", "12 C", 1),
                DailyWeather(0, "пн", "12 C", 1),
                DailyWeather(0, "пн", "12 C", 1),
                DailyWeather(0, "пн", "12 C", 1),
            )
        ) { index, item ->
            DsDailyWeatherItem(dailyWeather = item)
        }
    }
}

@Composable
fun DsDailyWeatherItem(dailyWeather: DailyWeather) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = dailyWeather.dayOfWeek
            )
            Text(
                text = dailyWeather.temp,
                modifier = Modifier
                    .padding(horizontal = 5.dp)

            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "image",
                modifier = Modifier
                    .size(50.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth()
                .background(Color.White)
                .size(0.dp,1.dp)
        )

        LazyRow(
            Modifier
                .clip(RoundedCornerShape(20.dp))

        ) {
            itemsIndexed(
                listOf(
                    HourlyWeather(0, "00:00", 1, "12 C"),
                    HourlyWeather(0, "00:00", 1, "12 C"),
                    HourlyWeather(0, "00:00", 1, "12 C"),
                    HourlyWeather(0, "00:00", 1, "12 C"),
                    HourlyWeather(0, "00:00", 1, "12 C"),
                    HourlyWeather(0, "00:00", 1, "12 C"),
                    HourlyWeather(0, "00:00", 1, "12 C"),
                    HourlyWeather(0, "00:00", 1, "12 C"),
                    HourlyWeather(0, "00:00", 1, "12 C"),
                )
            ) { index, item ->
                DsHourlyWeatherItem(hourlyWeather = item)
            }
        }
    }
}

@Composable
fun DsHourlyWeatherItem(hourlyWeather: HourlyWeather) {

    Column(
        Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = hourlyWeather.time,
            fontSize = 12.sp
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "image",
            modifier = Modifier
                .size(50.dp)
        )
        Text(
            text = hourlyWeather.temp,
            fontSize = 12.sp
        )
    }
}