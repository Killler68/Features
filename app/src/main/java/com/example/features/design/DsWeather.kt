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
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.viewmodel.WeatherViewModel

@Composable
fun DsWeather(viewModel: WeatherViewModel) {

//    private val viewModel: WeatherViewModel by viewModels { getViewModelFactory() }


    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DsWeatherActionBar()
        DsWeatherPreviewBar(viewModel)
        DsDailyWeatherPanel(viewModel)
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
fun DsWeatherPreviewBar(viewModel: WeatherViewModel) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Cyan),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = viewModel.getPreviewBarWeather().date,
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = Color.White
        )
        Image(
            painter = painterResource(viewModel.getPreviewBarWeather().icon),
            contentDescription = "image"
        )
        Text(
            text = viewModel.getPreviewBarWeather().temp,
            modifier = Modifier
                .padding(10.dp),
            fontSize = 30.sp,
            color = Color.White
        )
        Text(
            text = viewModel.getPreviewBarWeather().description,
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
fun DsDailyWeatherPanel(viewModel: WeatherViewModel) {

    LazyColumn(
        Modifier
    ) {
        itemsIndexed(
            viewModel.getWeather()
        ) { index, item ->
            DsDailyWeatherItem(dailyWeather = item, viewModel)
        }
    }
}

@Composable
fun DsDailyWeatherItem(dailyWeather: DailyWeather?, viewModel: WeatherViewModel) {

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
            if (dailyWeather != null) {
                Text(
                    text = dailyWeather.dayOfWeek
                )

            }

            if (dailyWeather != null) {
                Text(
                    text = dailyWeather.temp,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)

                )


            }
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
                .size(0.dp, 1.dp)
        )

        LazyRow(
            Modifier
                .clip(RoundedCornerShape(20.dp))

        ) {
            itemsIndexed(
                viewModel.getHoursWeather()
            ) { index, item ->
                DsHourlyWeatherItem(hoursWeather = item)
            }
        }
    }
}

@Composable
fun DsHourlyWeatherItem(hoursWeather: HoursWeather) {

    Column(
        Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = hoursWeather.time,
            fontSize = 12.sp
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "image",
            modifier = Modifier
                .size(50.dp)
        )
        Text(
            text = hoursWeather.temp,
            fontSize = 12.sp
        )
    }
}