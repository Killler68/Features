package com.example.features.common.api

import WeatherResponse
import androidx.compose.runtime.MutableState
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val WEATHER = "https://api.openweathermap.org/"
const val API_KEY = "1fb564b0448c20cff8c8a08d408dba5b"

interface WeatherApi {
    @GET("data/2.5/forecast?&appid=1fb564b0448c20cff8c8a08d408dba5b&units=metric")
    suspend fun getWeather(
        @Query("q") city: String
    ): WeatherResponse
}

object WeatherRetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(WEATHER)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)
}