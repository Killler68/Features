package com.example.features.common.api

import androidx.compose.runtime.MutableState
import com.example.features.weather.model.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val WEATHER_URL = "https://api.weatherapi.com/v1/"
const val WEATHER_API_KEY = "d4261ea19cfd4ee59b0212506240810 "

interface WeatherApi {

    @GET("forecast.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") city: MutableState<String>,
        @Query("days") days: Int
    ): WeatherResponse
}

object RetrofitClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(WEATHER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)
}