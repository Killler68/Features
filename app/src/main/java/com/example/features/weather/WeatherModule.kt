package com.example.features.weather

import androidx.lifecycle.ViewModel
import com.example.features.weather.viewmodel.WeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
class WeatherModule {

    @Provides
    @IntoMap
    @ClassKey(WeatherViewModel::class)
    fun provideWeatherViewModel(): ViewModel = WeatherViewModel()
}