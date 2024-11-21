package com.example.features.weather.repository

import androidx.compose.runtime.mutableStateOf
import com.example.features.common.api.RetrofitClient
import com.example.features.common.api.WEATHER_API_KEY
import com.example.features.common.extension.dateFormatDaily
import com.example.features.common.extension.dateFormatHourly
import com.example.features.common.extension.dateFormatPreview
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.usecase.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

var city = mutableStateOf("London")


class WeatherRepositoryImpl : WeatherRepository {
    override suspend fun getHoursWeather(): List<HoursWeather> =
        withContext(Dispatchers.IO) {
            val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, city, 1)
            response.forecast.forecastday.flatMap { forecastDay ->
                forecastDay.hour.map { hour ->
                    HoursWeather(
                        hour.time.dateFormatHourly(),
                        hour.temp_c,
                        hour.condition.icon
                    )
                }
            }
        }

    override suspend fun getDailyWeather(): List<DailyWeather> =
        withContext(Dispatchers.IO) {
            val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, city, 7)
            response.forecast.forecastday.map { days ->
                DailyWeather(
                    days.date.dateFormatDaily(),
                    days.day.maxtemp_c,
                    days.day.mintemp_c,
                    days.day.condition.icon
                )
            }
        }

    override suspend fun previewBarWeather(): PreviewBarWeather =
        withContext(Dispatchers.IO) {
            val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, city, 1)
            PreviewBarWeather(
                response.location.name,
                response.current.last_updated.dateFormatPreview(),
                response.current.condition.icon,
                response.current.temp_c,
                response.current.condition.text
            )
        }
}
