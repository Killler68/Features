package com.example.features.weather.di

import com.example.features.weather.data.WeatherRepositoryImpl
import com.example.features.weather.domain.usecase.WeatherPreviewUseCase
import com.example.features.weather.domain.usecase.WeatherRepository
import com.example.features.weather.domain.usecase.WeatherWeekUseCase
import com.example.features.weather.presentation.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WeatherModule {

    val modules = module {
        single<WeatherRepository> { WeatherRepositoryImpl() }
        factory { WeatherPreviewUseCase(get()) }
        factory { WeatherWeekUseCase(get()) }
        viewModel { WeatherViewModel(get(), get()) }
    }
}