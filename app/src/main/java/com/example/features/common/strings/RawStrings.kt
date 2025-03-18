package com.example.features.common.strings

const val OPEN_WEATHER_MAP = "https://api.openweathermap.org/"
const val WEATHER_FORECAST_API_KEY = "data/2.5/forecast?&appid=1fb564b0448c20cff8c8a08d408dba5b"
const val WEATHER_METRICS = "&units=metric"
const val WEATHER_LANGUAGES = "&lang=ru"

const val WEATHER_API =
    OPEN_WEATHER_MAP + WEATHER_FORECAST_API_KEY + WEATHER_METRICS + WEATHER_LANGUAGES

var city = "Moscow"
