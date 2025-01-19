package com.example.features.weather.model

data class WeatherResponse(
    val location: Location,
    val forecast: Forecast,
    val current: Current,
    val humidity: Int,
    val uv: Float,
    val pressure_mb: Float,
    val wind_kph: Float,

)

data class Location(
    val name: String
)

data class Current(
    val last_updated: String,
    val temp_c: Float,
    val condition: Condition

)
data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<HourWeather>
)

data class Day(
    val maxtemp_c: Float,
    val mintemp_c: Float,
    val avgtemp_c: Float,
    val daily_chance_of_rain: Int,
    val condition: Condition
)

data class HourWeather(
    val time: String,
    val temp_c: Float,
    val condition: Condition
)

data class Condition(
    val text: String,
    val icon: String
)
