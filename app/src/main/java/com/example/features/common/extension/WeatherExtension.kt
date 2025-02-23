package com.example.features.common.extension

import com.example.features.R

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
        "Ottawa"  -> "Оттава"
        "London" -> "Лондон"
        "Moscow" -> "Москва"
        else -> this
    }
}
fun String.getRawNameFeaturesCityEngToRuExtension(): String {

    return when (this) {
        "Voronezh" -> "Воронеже"
        "Ottawa"  -> "Оттаве"
        "London" -> "Лондоне"
        "Moscow" -> "Москве"
        else -> this
    }
}

fun extensionConditionWeather(condition: String): Int {
    return when (condition) {
        "Overcast" -> R.raw.gif_clouds
        "Mist" -> R.raw.gif_clouds
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

fun extensionTemperatureWeather(float: Double): Int = when {
    float < 0 -> R.drawable.image_girl_ice
    float < 10 -> R.raw.image_cold_children
    float < 20 -> R.raw.image_child_warm
    float < 30 -> R.raw.gif_girl_sun
    else -> R.raw.image_child_warm
}