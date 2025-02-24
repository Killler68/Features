package com.example.features.common.extension

import androidx.compose.ui.graphics.Color
import com.example.features.R
import com.example.features.common.utils.ColorCategory

fun String.getRawNameWeatherExtension(): String {

    return when (this) {
        "Overcast" -> "Пасмурно"
        "Patchy rain nearby" -> "Мелкий дождь"
        "Light snow showers" -> "Небольшой снежный дождь"
        "Cloudy " -> "Облачный"
        "Light snow" -> "Легкий снегопад"
        "Heavy snow" -> "Сильный снегопад"
        "Partly Cloudy " -> "Переменная облачность"
        "Moderate rain " -> "Умеренный дождь"
        "Light drizzle " -> "Легкий моросящий дождь"
        "Light rain " -> "Небольшой дождь"
        "Blizzard " -> "Метель"
        "Light freezing rain " -> "Легкий ледяной дождь"
        "Moderate snow " -> "Умеренный снегопад"
        "Sunny " -> "Солнечный"
        "Mist" -> "Туман"
        else -> this
    }
}

fun String.getRawNameCityRuToEngExtension(): String {

    return when (this) {
        "Воронеж" -> "Voronezh"
        "Оттава" -> "Ottawa"
        "Лондон" -> "London"
        "Москва" -> "Moscow"
        else -> this
    }
}

fun String.getRawNameCityEngToRuExtension(): String {

    return when (this) {
        "Voronezh" -> "Воронеж"
        "Ottawa" -> "Оттава"
        "London" -> "Лондон"
        "Moscow" -> "Москва"
        else -> this
    }
}

fun String.getRawNameFeaturesCityEngToRuExtension(): String {

    return when (this) {
        "Voronezh" -> "Воронеже"
        "Ottawa" -> "Оттаве"
        "London" -> "Лондоне"
        "Moscow" -> "Москве"
        else -> this
    }
}

fun extensionConditionWeather(condition: String): Int {
    return when (condition) {
        "01d" -> R.raw.gif_clouds
        "02d" -> R.raw.gif_clouds
        "03d" -> R.raw.gif_rain
        "04d" -> R.raw.gif_snow
        "09d" -> R.raw.gif_clouds
        "10d" -> R.raw.gif_snow
        "11d" -> R.raw.gif_snow
        "13d" -> R.raw.gif_clouds
        "50d" -> R.raw.gif_rain
        "01n" -> R.raw.gif_rain
        "02n" -> R.raw.gif_rain
        "03n" -> R.raw.gif_snow
        "04n" -> R.raw.gif_rain
        "09n" -> R.raw.gif_snow
        "10n" -> R.raw.gif_sun
        else -> R.raw.gif_clouds
    }
}

fun String.imageWeatherExtension(): Int {
    return when (this) {
        "01d" -> R.drawable.sun
        "02d" -> R.drawable.sun
        "03d" -> R.drawable.sun
        "04d" -> R.drawable.clouds_sun
        "09d" -> R.drawable.heavy_rain
        "10d" -> R.drawable.heavy_rain
        "11d" -> R.drawable.thunder
        "13d" -> R.drawable.snow
        "50d" -> R.drawable.clouds_sun
        "01n" -> R.drawable.sun
        "02n" -> R.drawable.sun
        "03n" -> R.drawable.clouds_sun
        "04n" -> R.drawable.clouds_sun
        "09n" -> R.drawable.heavy_rain
        "10n" -> R.drawable.heavy_rain
        "11n" -> R.drawable.thunder
        "13n" -> R.drawable.snow
        "50n" -> R.drawable.snow
        else -> R.drawable.sun
    }
}


fun extensionTemperatureWeather(float: Double): Int = when {
    float < 0 -> R.drawable.image_girl_ice
    float < 10 -> R.raw.image_cold_children
    float < 20 -> R.raw.image_child_warm
    float < 30 -> R.raw.gif_girl_sun
    else -> R.raw.image_child_warm
}


fun weatherColorExtension(condition: String, colorCategory: ColorCategory): Color {
    return when (condition) {
        "d" -> {
            when (colorCategory) {
                ColorCategory.BACKGROUND -> Color(0xFF87CEEB)
                ColorCategory.CARD -> Color(0xFFADD8E6)
                ColorCategory.TEXT -> Color.Black
            }
        }

        "n" -> {
            when (colorCategory) {
                ColorCategory.BACKGROUND -> Color(0xFF1C1C3C)
                ColorCategory.CARD -> Color(0xFF2A2A5A)
                ColorCategory.TEXT -> Color.White
            }
        }

        else -> {
            Color.Red
        }
    }
}