package com.example.features.weather

import com.example.features.weather.repository.WeatherRepositoryImpl
import com.example.features.weather.usecase.WeatherPreviewBarUseCase
import com.example.features.weather.usecase.WeatherRepository
import com.example.features.weather.usecase.WeatherUseCase
import com.example.features.weather.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WeatherModule {

    val modules = module {
        single<WeatherRepository> { WeatherRepositoryImpl() }
        factory { WeatherPreviewBarUseCase(get()) }
        factory { WeatherUseCase(get()) }
        viewModel { WeatherViewModel(get(), get()) }
    }
}