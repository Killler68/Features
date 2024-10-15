package com.example.features.weather.repository

import android.annotation.SuppressLint
import com.example.features.common.api.RetrofitClient
import com.example.features.common.api.WEATHER_API_KEY
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.usecase.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherRepositoryImpl : WeatherRepository {
    override suspend fun getHoursWeather(): List<HoursWeather> =
        withContext(Dispatchers.IO) {
            val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, "Москва", 1)
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
            val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, "Москва", 7)
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
            val response = RetrofitClient.weatherApi.getWeather(WEATHER_API_KEY, "Москва", 1)
            PreviewBarWeather(
                response.location.name,
                response.current.last_updated.dateFormatPreview(),
                response.current.condition.icon,
                response.current.temp_c,
                response.current.condition.text
            )
        }
}

const val DATE_FORMAT_PREVIEW = "Сегодня d MMM EEE"
const val DATE_FORMAT_HOURLY = "H:mm"
const val DATE_FORMAT_DAILY = "MMM d EEE"
const val INPUT_DATE_FORMAT = "yyyy-MM-dd HH:mm"
const val INPUT_DATE_FORMAT_DAILY = "yyyy-MM-dd"
const val LANGUAGE = "ru"

@SuppressLint("ConstantLocale")
val inputDateFormat = SimpleDateFormat(INPUT_DATE_FORMAT, Locale.getDefault())
val inputDateFormatDaily = SimpleDateFormat(INPUT_DATE_FORMAT_DAILY, Locale.getDefault())
val dateFormatPreview = SimpleDateFormat(DATE_FORMAT_PREVIEW, Locale(LANGUAGE))
val dateFormatHourly = SimpleDateFormat(DATE_FORMAT_HOURLY, Locale(LANGUAGE))
val dateFormatDaily = SimpleDateFormat(DATE_FORMAT_DAILY, Locale(LANGUAGE))

fun String.dateFormatPreview(): String {

    val parseDate = inputDateFormat.parse(this)
    return parseDate.let { dateFormatPreview.format(it!!) }
}

fun String.dateFormatDaily(): String {

    val parseDate = inputDateFormatDaily.parse(this)
    return parseDate.let { dateFormatDaily.format(it!!) }
}

fun String.dateFormatHourly(): String {

    val parseDate = inputDateFormat.parse(this)
    return parseDate.let { dateFormatHourly.format(it!!) }
}