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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.features.R
import com.example.features.common.extension.getRawNameCityEngToRuExtension
import com.example.features.common.extension.getRawNameCityRuToEngExtension
import com.example.features.common.extension.getRawNameWeatherExtension
import com.example.features.ui.theme.Cyan
import com.example.features.ui.theme.LightGray
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.model.WeatherEvent
import com.example.features.weather.model.WeatherSideEffect
import com.example.features.weather.model.WeatherState
import com.example.features.weather.repository.city
import com.example.features.weather.screen.ErrorScreen
import com.example.features.weather.screen.LoadingScreen
import com.example.features.weather.viewmodel.WeatherViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DsWeather(navController: NavController) {

    val viewModel: WeatherViewModel = getViewModel()
    val state by viewModel.state.collectAsState()
    val effectFlow = viewModel.effect

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is WeatherSideEffect.NavigateTo -> navController.navigate(effect.route)
            }
        }
    }

    when (state) {
        WeatherState.Loading -> LoadingScreen()
        is WeatherState.Success -> WeatherContent(state as WeatherState.Success)

        is WeatherState.Error -> ErrorScreen(
            R.drawable.weather,
            "Ошибка"
        )
    }
}

@Composable
fun WeatherContent(state: WeatherState.Success) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DsWeatherActionBar(state.preview)
        DsWeatherPreviewBar(state.preview)
        DsDailyWeatherPanel(state.dayly, state.hours)
    }
}

@Composable
fun DsWeatherActionBar(preview: PreviewBarWeather) {

    val viewModel: WeatherViewModel = getViewModel()
    var editCity by remember { mutableStateOf(preview.city) }

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
                .clickable { viewModel.dispatch(WeatherEvent.ToBack) }
        )

        Text(
            text = preview.city.getRawNameCityEngToRuExtension(),
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
                    .clickable { viewModel.isEnabled.value = true }
            )

            if (viewModel.isEnabled.value) {
                DsChangeLocation(
                    title = editCity.getRawNameCityEngToRuExtension(),
                    onCityChange = { editCity = it },
                    onDismiss = { viewModel.isEnabled.value = false },
                    onSave = {
                        preview.city = editCity

                        city.value = editCity.getRawNameCityRuToEngExtension()

                        viewModel.dispatch(WeatherEvent.LoadData)
                        viewModel.isEnabled.value = false
                    }
                )
            }
        }
    }
}

@Composable
fun DsWeatherPreviewBar(preview: PreviewBarWeather) {


    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Cyan),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = preview.date,
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = Color.White
        )
        Image(
            painter = rememberImagePainter(data = "https:" + preview.icon),
            contentDescription = "image",
            modifier = Modifier
                .size(100.dp)
        )
        Text(
            text = preview.temp.toString(),
            modifier = Modifier
                .padding(10.dp),
            fontSize = 30.sp,
            color = Color.White
        )
        Text(
            text = preview.description.getRawNameWeatherExtension(),
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
fun DsDailyWeatherPanel(dailyWeather: List<DailyWeather>, hoursWeather: List<HoursWeather>) {

    val viewModel: WeatherViewModel = getViewModel()

    LazyColumn {
        itemsIndexed(
            dailyWeather
        ) { index, item ->
            DsDailyWeatherItem(
                dailyWeather = item,
                hoursWeather
            ) { viewModel.dispatch(WeatherEvent.ToWeatherDetailed(index)) }
        }
    }
}

@Composable
fun DsDailyWeatherItem(
    dailyWeather: DailyWeather,
    hoursWeather: List<HoursWeather>,
    onClick: () -> Unit
) {

    val integerValueMax = dailyWeather.maxTemp.toInt()
    val integerValueMin = dailyWeather.minTemp.toInt()
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray)
            .clickable {
                onClick()
            }
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
                hoursWeather
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
            painter = rememberAsyncImagePainter("https:" + hoursWeather.icon),
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