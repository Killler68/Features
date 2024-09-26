package com.example.features.common

import com.example.features.common.application.ApplicationModule
import com.example.features.weather.WeatherModule
import dagger.Module

@Module(
    includes = [
        ApplicationModule::class,
        WeatherModule::class
    ]
)
class FeaturesModule {
}