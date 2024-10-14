package com.example.features.weather.repository

import android.content.Context
import com.example.features.common.api.RetrofitClient
import com.example.features.common.api.WEATHER_API_KEY
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.usecase.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WeatherRepositoryImpl(
) : WeatherRepository {
    override suspend fun getHoursWeather(context: Context): List<HoursWeather> =
        withContext(Dispatchers.IO) {

            val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, "Москва", 1)
            response.forecast.forecastday.flatMap { forecastDay ->
                forecastDay.hour.map { hour ->
                    HoursWeather(
                        hour.time,
                        hour.temp_c,
                        hour.condition.icon
                    )
                }
            }
        }

    override suspend fun getDailyWeather(): List<DailyWeather> =
        withContext(Dispatchers.IO) {

            val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, "Москва", 3)

            response.forecast.forecastday.map { days ->
                DailyWeather(
                    days.date,
                    days.day.maxtemp_c,
                    days.day.condition.icon
                )
            }
        }


    override suspend fun previewBarWeather(): PreviewBarWeather =
        withContext(Dispatchers.IO) {
            val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, "Москва", 1)
            PreviewBarWeather(
                response.current.last_updated,
                response.current.condition.icon,
                response.current.temp_c,
                response.current.condition.text
            )


        }
}
//private val weatherPanelTest = listOf(
//    DailyWeather(0, "123123", "sada", 1),
//    DailyWeather(1, "31232", "2sa", 1),
//    DailyWeather(2, "3sadasdasd", "3", 1),
//    DailyWeather(3, "4ыфв", "4", 1),
//    DailyWeather(4, "4аф", "4", 1),
//    DailyWeather(5, "41231ыф", "4", 1),
//)

//
//private val previewBarWeatherTest =
//    PreviewBarWeather(
//        id = 0,
//        date = "Сегодня 5 окт. сб",
//        icon = R.drawable.ic_launcher_foreground,
//        temp = "20",
//        description = "Солнечно"
//    )
