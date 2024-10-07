package com.example.features.weather

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.features.weather.repository.WeatherRepositoryImpl
import com.example.features.weather.usecase.HoursWeatherUseCaseImpl
import com.example.features.weather.usecase.PreviewBarWeatherUseCaseImpl
import com.example.features.weather.usecase.WeatherRepository
import com.example.features.weather.usecase.WeatherUseCaseImpl
import com.example.features.weather.viewmodel.HoursWeatherUseCase
import com.example.features.weather.viewmodel.PreviewBarWeatherUseCase
import com.example.features.weather.viewmodel.WeatherUseCase
import com.example.features.weather.viewmodel.WeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
class WeatherModule {

    @Provides
    fun provideWeatherRepository(): WeatherRepository = WeatherRepositoryImpl()

    @Provides
    fun provideWeatherUseCase(weatherRepository: WeatherRepository): WeatherUseCase =
        WeatherUseCaseImpl(weatherRepository)

    @Provides
    fun provideHoursWeatherUseCase(weatherRepository: WeatherRepository): HoursWeatherUseCase =
        HoursWeatherUseCaseImpl(weatherRepository)

    @Provides
    fun providePreviewBarWeather(weatherRepository: WeatherRepository): PreviewBarWeatherUseCase =
        PreviewBarWeatherUseCaseImpl(weatherRepository)

    @Provides
    fun provideNavController(context: Context) = NavController(context)

    @Provides
    @IntoMap
    @ClassKey(WeatherViewModel::class)
    fun provideWeatherViewModel(
        weatherUseCase: WeatherUseCase,
        hoursWeatherUseCase: HoursWeatherUseCase,
        previewBarWeatherUseCase: PreviewBarWeatherUseCase,
        navController: NavController
    ): ViewModel =
        WeatherViewModel(
            weatherUseCase,
            hoursWeatherUseCase,
            previewBarWeatherUseCase,
            navController
        )
}