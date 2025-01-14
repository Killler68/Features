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

var city = mutableStateOf("London")


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

    override fun getWeatherDetailedDay(): WeatherDetailedDay = testWeatherDetailedDay
    override fun getWeatherHoursDay(): List<WeatherHoursDay> = testWeatherHoursDay

    override fun getWeatherAdditionalInfoDay(): WeatherAdditionalInfoDay =
        testWeatherAdditionalInfoDay


    private val testWeatherDetailedDay = WeatherDetailedDay(
        0,
        "1 C",
        "Облачно",
        "1 С",
        "4 С",
        "-1 С",
        "-3 С",
    )

    private val testWeatherHoursDay = listOf(
        WeatherHoursDay("00:00", "img 1", "1 C", "1%"),
        WeatherHoursDay("01:00", "img 2", "2 C", "3%"),
        WeatherHoursDay("02:00", "img 3", "1 C", "3%"),
        WeatherHoursDay("03:00", "img 4", "2 C", "2%"),
        WeatherHoursDay("04:00", "img 5", "2 C", "2%"),
        WeatherHoursDay("05:00", "img 6", "1 C", "1%"),
        WeatherHoursDay("06:00", "img 7", "2 C", "1%"),
        WeatherHoursDay("07:00", "img 8", "1 C", "1%"),
        WeatherHoursDay("08:00", "img 9", "2 C", "2%"),
        WeatherHoursDay("09:00", "img 10", "1 C", "1%"),
        WeatherHoursDay("11:00", "img 11", "1 C", "1%"),
        WeatherHoursDay("12:00", "img 12", "2 C", "3%"),
    )

    private val testWeatherAdditionalInfoDay = WeatherAdditionalInfoDay(
        "Среда",
        "Температура немного ниже чем сегодня",
        "3 С",
        "Ожидается что Среда будет тоже солнечным днем",
        "Солнце взойдет в 08:25",
        "Низкий",
        "81%",
        "11км/ч",
        "1033,2 мбар"
    )
}
