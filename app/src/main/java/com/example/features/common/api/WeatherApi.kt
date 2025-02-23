package com.example.features.common.api

import WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val WEATHER = "https://api.openweathermap.org/"
const val API_KEY = "1fb564b0448c20cff8c8a08d408dba5b"

interface WeatherApi {
    @GET("data/2.5/forecast?lat=44.34&lon=10.99&appid=1fb564b0448c20cff8c8a08d408dba5b&units=metric")
    suspend fun getWeather(): WeatherResponse
}

object WeatherRetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(WEATHER)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)
}