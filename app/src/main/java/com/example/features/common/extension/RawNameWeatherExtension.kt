package com.example.features.common.extension

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