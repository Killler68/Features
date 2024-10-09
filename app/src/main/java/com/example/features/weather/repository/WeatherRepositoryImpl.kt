package com.example.features.weather.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.features.R
import com.example.features.weather.model.DailyWeather
import com.example.features.weather.model.HoursWeather
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.usecase.WeatherRepository
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

const val WEATHER_API_KEY = "d4261ea19cfd4ee59b0212506240810 "

class WeatherRepositoryImpl() : WeatherRepository {

    override suspend fun getHoursWeather(context: Context): List<HoursWeather> {
        return suspendCoroutine { continuation ->
            val url = "https://api.weatherapi.com/v1/forecast.json?key=" +
                    WEATHER_API_KEY +
                    "&q=London" +
                    "&days=3&aqi=no&alerts=no"

            val queue = Volley.newRequestQueue(context)
            val sRequest = StringRequest(
                Request.Method.GET,
                url,
                { response ->
                    val list = getWeatherByDays(response)
                    continuation.resume(list) // Возвращаем результат через continuation
                },
                { error ->
                    continuation.resumeWithException(error)
                }
            )
            queue.add(sRequest)
        }
    }

    override fun getWeather(): List<DailyWeather> = weatherPanelTest
//    override fun getHoursWeather(): List<HoursWeather> {
//
//    }

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


private val previewBarWeatherTest =
    PreviewBarWeather(
        id = 0,
        date = "Сегодня 5 окт. сб",
        icon = R.drawable.ic_launcher_foreground,
        temp = "20",
        description = "Солнечно"
    )


private fun getWeatherByDays(response: String): List<HoursWeather> {
    if (response.isEmpty()) return listOf()

    val list = ArrayList<HoursWeather>()
    val mainObject = JSONObject(response)
    val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")

    // Получаем текущую погоду (например, для отображения иконки)
    val currentCondition = mainObject.getJSONObject("current").getJSONObject("condition")

    for (i in 0 until days.length()) {
        val item = days[i] as JSONObject
        val hours = item.getJSONArray("hour")

        // Проходим по каждому часу
        for (j in 0 until hours.length()) { // Заменено на `until`
            val hour = hours[j] as JSONObject
            val time = hour.getString("time").split(" ")[1] // Берем вторую часть, где содержится время (часы и минуты)

            list.add(
                HoursWeather(
                    time = time, // Время
                    icon = currentCondition.getString("icon"), // Иконка текущей погоды
                    temp = hour.getString("temp_c") // Температура для этого часа
                )
            )
        }
    }

    return list
}