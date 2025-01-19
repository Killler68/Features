package com.example.features.weather.repository

import androidx.compose.runtime.mutableStateOf
import com.example.features.common.api.RetrofitClient
import com.example.features.common.api.WEATHER_API_KEY
import com.example.features.common.extension.dateFormatDaily
import com.example.features.common.extension.dateFormatHourly
import com.example.features.common.extension.dateFormatPreview
import com.example.features.weather.detailed.usecase.WeatherDetailedRepository
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.model.WeatherAdditionalInfoDay
import com.example.features.weather.model.WeatherDetailedDay
import com.example.features.weather.model.WeatherHoursDay
import com.example.features.weather.usecase.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

var city = mutableStateOf("London   ")


class WeatherRepositoryImpl : WeatherRepository, WeatherDetailedRepository {
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

    override suspend fun getWeatherDetailedDay(): WeatherDetailedDay = withContext(Dispatchers.IO) {
        val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, city, 1)
        WeatherDetailedDay(
            response.current.temp_c,
            response.current.condition.text,
            response.forecast.forecastday.first().day.maxtemp_c,
            response.forecast.forecastday.first().day.mintemp_c,
            response.wind_degree,
        )
    }

    override suspend fun getWeatherHoursDay(): List<WeatherHoursDay> = withContext(Dispatchers.IO) {
        val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, city, 1)
        val days = response.forecast.forecastday
        days.flatMap { day ->
            day.hour.map { hour ->
                WeatherHoursDay(
                    hour.time.dateFormatHourly(),
                    hour.condition.icon,
                    hour.temp_c,
                    hour.temp_c
                )
            }
        }
    }

    override suspend fun getWeatherAdditionalInfoDay(): WeatherAdditionalInfoDay =
        withContext(Dispatchers.IO) {
            val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, city, 1)
            WeatherAdditionalInfoDay(
                response.current.temp_c,
                response.current.condition.text,
            )
        }
}
