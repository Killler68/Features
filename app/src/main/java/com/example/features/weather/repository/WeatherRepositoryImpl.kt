package com.example.features.weather.repository

import com.example.features.R
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.usecase.WeatherRepository

class WeatherRepositoryImpl() : WeatherRepository {

    override fun getWeather(): List<DailyWeather> = weatherPanelTest
    override fun getHoursWeather(): List<HoursWeather> = hoursWeatherTest
    override fun previewBarWeather(): PreviewBarWeather = previewBarWeatherTest
}

private val weatherPanelTest = listOf(
    DailyWeather(0, "123123", "sada", 1),
    DailyWeather(1, "31232", "2sa", 1),
    DailyWeather(2, "3sadasdasd", "3", 1),
    DailyWeather(3, "4ыфв", "4", 1),
    DailyWeather(4, "4аф", "4", 1),
    DailyWeather(5, "41231ыф", "4", 1),
)

private val hoursWeatherTest = listOf(
    HoursWeather(0, "00:00", 1, "10 C"),
    HoursWeather(1, "02:00", 1, "15 C"),
    HoursWeather(2, "03:00", 1, "20 C"),
    HoursWeather(3, "04:00", 1, "30 C"),
    HoursWeather(4, "05:00", 1, "40 C"),
    HoursWeather(5, "06:00", 1, "50 C"),
    HoursWeather(6, "07:00", 1, "60 C"),
)

private val previewBarWeatherTest =
    PreviewBarWeather(
        id = 0,
        date = "Сегодня 5 окт. сб",
        icon = R.drawable.ic_launcher_foreground,
        temp = "20",
        description = "Солнечно"
    )