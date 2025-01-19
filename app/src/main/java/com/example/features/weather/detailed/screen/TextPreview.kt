package com.example.features.weather.detailed.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.features.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TextPreview(
    temp: Float,
    description: String,
    maxTemp: Float,
    minTemp: Float,
    feelingTemp: Int
) {

    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
        ) {
            Text(
                text = temp.toString(),
                fontSize = 50.sp,
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 20.dp),
                color = Color.White
            )
            Text(
                text = description,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp, bottom = 20.dp),
                color = Color.White
            )
            Text(
                text = "$maxTemp / $minTemp Ощущается как $feelingTemp",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                color = Color.White
            )
        }

        Column {

            GlideImage(
                model = extensionConditionWeather(description),
                contentDescription = "animated_gif_weather",
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .height(50.dp)
                    .width(100.dp),
                contentScale = ContentScale.Inside
            )
            GlideImage(
                model = extensionTemperatureWeather(temp),
                contentDescription = "animated_gif_children",
                modifier = Modifier
                    .height(150.dp)
                    .width(100.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

fun extensionConditionWeather(condition: String): Int {
    return when (condition) {
        "Overcast" -> R.raw.gif_sun
        "Mist" -> R.raw.gif_sun
        "Patchy rain nearby" -> R.raw.gif_rain
        "Light snow showers" -> R.raw.gif_snow
        "Cloudy " -> R.raw.gif_clouds
        "Light snow" -> R.raw.gif_snow
        "Heavy snow" -> R.raw.gif_snow
        "Partly Cloudy " -> R.raw.gif_clouds
        "Moderate rain " -> R.raw.gif_rain
        "Light drizzle " -> R.raw.gif_rain
        "Light rain " -> R.raw.gif_rain
        "Blizzard " -> R.raw.gif_snow
        "Light freezing rain " -> R.raw.gif_rain
        "Moderate snow " -> R.raw.gif_snow
        "Sunny " -> R.raw.gif_sun
        else -> R.raw.gif_clouds
    }
}

fun extensionTemperatureWeather(float: Float): Int =
    if (float < 0) R.raw.gif_child_cool
    else if (float < 10) R.raw.gif_cold_children
    else if (float < 20) R.raw.child_warm
    else if (float < 30) R.raw.gif_girl_sun
    else R.raw.gif_sun
