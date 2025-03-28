package com.example.features.common.extension

import androidx.compose.ui.graphics.Color
import com.example.features.R
import com.example.features.common.utils.ColorCategory
import com.example.features.common.utils.DayAndNight
import java.util.Calendar
import java.util.TimeZone

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

fun String.getRawNameFeaturesCityEngToRuExtension(): String {

    return when (this) {
        "Voronezh" -> "Воронеже"
        "Ottawa" -> "Оттаве"
        "London" -> "Лондоне"
        "Moscow" -> "Москве"
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

fun String.extensionConditionWeather(condition: String): Int {
    return when (condition) {
        DayAndNight.DAY.condition -> {
            return when (this) {
                "01d" -> R.raw.gif_sun
                "02d" -> R.raw.gif_sun
                "03d" -> R.raw.gif_sun
                "04d" -> R.raw.gif_clouds
                "09d" -> R.raw.gif_rain
                "10d" -> R.raw.gif_rain
                "11d" -> R.raw.gif_lightning
                "13d" -> R.raw.gif_snow
                "50d" -> R.raw.gif_clouds
                "01n" -> R.raw.gif_sun
                "02n" -> R.raw.gif_sun
                "03n" -> R.raw.gif_clouds
                "04n" -> R.raw.gif_clouds
                "09n" -> R.raw.gif_rain
                "10n" -> R.raw.gif_rain
                "11n" -> R.raw.gif_lightning
                "13n" -> R.raw.gif_snow
                "50n" -> R.raw.gif_snow
                else -> R.raw.gif_clouds
            }
        }

        DayAndNight.NIGHT.condition -> {
            return when (this) {
                "01d" -> R.raw.gif_moon
                "02d" -> R.raw.gif_moon
                "03d" -> R.raw.gif_moon
                "04d" -> R.raw.gif_clouds
                "09d" -> R.raw.gif_rain
                "10d" -> R.raw.gif_rain
                "11d" -> R.raw.gif_lightning
                "13d" -> R.raw.gif_snow
                "50d" -> R.raw.gif_clouds
                "01n" -> R.raw.gif_moon
                "02n" -> R.raw.gif_moon
                "03n" -> R.raw.gif_clouds
                "04n" -> R.raw.gif_clouds
                "09n" -> R.raw.gif_rain
                "10n" -> R.raw.gif_rain
                "11n" -> R.raw.gif_lightning
                "13n" -> R.raw.gif_snow
                "50n" -> R.raw.gif_snow
                else -> R.raw.gif_moon
            }
        }

        else -> {
            R.raw.gif_clouds
        }
    }
}

fun Long.getHourOfDay(): Int {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
        timeInMillis = this@getHourOfDay * 1000
    }
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    return hour
}

sealed class IconWeather {
    data object SunAndMoon : IconWeather()
    data object CloudAndMoonCloud : IconWeather()
}

fun Long.isIcon(iconWeather: IconWeather): Int {
    val hour = this.getHourOfDay()

    return when (iconWeather) {
        IconWeather.CloudAndMoonCloud -> {
            when (hour) {
                in 0..5, in 18..23 -> R.drawable.moon_cloud
                in 6..17 -> R.drawable.clouds_sun
                else -> R.drawable.clouds_sun
            }
        }

        IconWeather.SunAndMoon -> {
            when (hour) {
                in 0..5, in 18..23 -> R.drawable.moon
                in 6..17 -> R.drawable.sun
                else -> R.drawable.sun
            }
        }
    }
}

fun String.imageWeatherExtension(dtText: Long): Int {
    return when (this) {
        "01d", "02d", "01n", "02n" -> dtText.isIcon(IconWeather.SunAndMoon)
        "03d", "03n", "04d", "04n" -> dtText.isIcon(IconWeather.CloudAndMoonCloud)
        "09d", "09n", "10d", "10n" -> R.drawable.heavy_rain
        "11d", "11n" -> R.drawable.thunder
        "13d", "13n" -> R.drawable.snow
        "50d", "50n" -> R.drawable.snow
        else -> dtText.isIcon(IconWeather.SunAndMoon)
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
        DayAndNight.DAY.condition -> {
            when (colorCategory) {
                ColorCategory.BACKGROUND -> Color(0xFF89CFEC)
                ColorCategory.CARD -> Color(0xFFADD8E6)
                ColorCategory.DIALOG -> Color(0xFF84C4DA)
                ColorCategory.INDICATORS -> Color.White
                ColorCategory.IMAGE -> Color.Black
                ColorCategory.TEXT -> Color.Black
            }
        }

        DayAndNight.NIGHT.condition -> {
            when (colorCategory) {
                ColorCategory.BACKGROUND -> Color(0xFF212142)
                ColorCategory.CARD -> Color(0xFF2A2A5A)
                ColorCategory.DIALOG -> Color(0xFF343469)
                ColorCategory.INDICATORS -> Color.LightGray
                ColorCategory.IMAGE -> Color.White
                ColorCategory.TEXT -> Color.White
            }
        }

        else -> {
            Color.Red
        }
    }
}


fun weatherFormatVisibility(visibility: Int): String = when {
    visibility < 100 -> "Плохая"
    visibility < 10000 -> "Средняя"
    visibility == 10000 -> "Хорошая"
    else -> "Неизвестно"
}