package com.example.features.weather.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.features.R
import com.example.features.common.extension.getRawNameWeatherExtension
import com.example.features.navigation.Screens
import com.example.features.ui.theme.Cyan
import com.example.features.ui.theme.LightGray
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.state.WeatherState
import com.example.features.weather.viewmodel.WeatherViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DsWeather(navController: NavController) {

    val viewModel: WeatherViewModel = getViewModel()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWeather()
    }
    when (state.value) {
        WeatherState.Loading -> DsLoading()
        is WeatherState.Content -> Content(
            viewModel,
            navController
        )

        is WeatherState.Error -> DsLoading()
    }
}

@Composable
fun Content(
    viewModel: WeatherViewModel,
    navController: NavController
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DsWeatherActionBar(viewModel, navController)
        DsWeatherPreviewBar(viewModel)
        DsDailyWeatherPanel(viewModel)
    }
}

@Composable
fun DsWeatherActionBar(viewModel: WeatherViewModel, navController: NavController) {

    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = "image_back",
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(LightGray)
                .padding(7.dp)
                .clickable { navController.navigate(Screens.Features.route) }
        )

        Text(
            text = viewModel.previewBarWeather.value.city,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(0.5f)
        )

        Box(
            modifier = Modifier
                .size(50.dp)
                .weight(0.1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "image_location",
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LightGray)
                    .padding(7.dp)
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
            text = viewModel.previewBarWeather.value.date,
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = Color.White
        )
        Image(
            painter = rememberImagePainter(data = "https:" + viewModel.previewBarWeather.value.icon),
            contentDescription = "image",
            modifier = Modifier
                .size(100.dp)
        )
        Text(
            text = viewModel.previewBarWeather.value.temp.toString(),
            modifier = Modifier
                .padding(10.dp),
            fontSize = 30.sp,
            color = Color.White
        )
        Text(
            text = viewModel.previewBarWeather.value.description.getRawNameWeatherExtension(),
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
            viewModel.dailyWeather.value
        ) { index, item ->
            DsDailyWeatherItem(dailyWeather = item, viewModel)
        }
    }
}

@Composable
fun DsDailyWeatherItem(
    dailyWeather: DailyWeather,
    viewModel: WeatherViewModel,
//    hoursList: MutableState<List<HoursWeather>>
) {
    val integerValueMax = dailyWeather.maxTemp.toInt()
    val integerValueMin = dailyWeather.minTemp.toInt()
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
                text = dailyWeather.dayOfWeek,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(0.3f)
                    .padding(start = 10.dp)
            )

            Text(
                text = "$integerValueMin°",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(0.3f)

            )
            Text(
                text = "$integerValueMax°",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(horizontal = 5.dp)

            )

            Image(
                painter = rememberImagePainter(data = "https:" + dailyWeather.icon),
                contentDescription = "image",
                modifier = Modifier
                    .size(30.dp)
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
                viewModel.hoursWeather.value
            ) { index, item ->
                DsHourlyWeatherItem(hoursWeather = item)
            }
        }
    }
}

@Composable
fun DsHourlyWeatherItem(hoursWeather: HoursWeather) {

    val integerValue = hoursWeather.temp.toInt()
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
            painter = rememberImagePainter(data = "https:" + hoursWeather.icon), // Загружаем иконку по URL
            contentDescription = "Weather Icon",
            modifier = Modifier
                .size(50.dp)
                .padding(8.dp)
        )
        Text(
            text = "$integerValue°",
            fontSize = 12.sp

        )
    }
}

@Composable
fun DsLoading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
//        CircularProgressIndicator()
        Text("asf")
    }
}