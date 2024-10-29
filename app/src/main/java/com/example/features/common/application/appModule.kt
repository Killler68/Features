package com.example.features.common.application

import com.example.features.MainViewModel
import com.example.features.notes.repository.NotesRepositoryImpl
import com.example.features.notes.usecase.AddNoteUseCaseImpl
import com.example.features.notes.usecase.DeleteNoteImpl
import com.example.features.notes.usecase.GetNotesUseCaseImpl
import com.example.features.notes.usecase.NotesRepository
import com.example.features.notes.usecase.UpdateNoteImpl
import com.example.features.notes.viewmodel.AddNoteUseCase
import com.example.features.notes.viewmodel.DeleteNote
import com.example.features.notes.viewmodel.GetNotesUseCase
import com.example.features.notes.viewmodel.NotesViewModel
import com.example.features.notes.viewmodel.UpdateNote
import com.example.features.weather.repository.WeatherRepositoryImpl
import com.example.features.weather.usecase.HoursWeatherUseCaseImpl
import com.example.features.weather.usecase.PreviewBarWeatherUseCaseImpl
import com.example.features.weather.usecase.WeatherRepository
import com.example.features.weather.usecase.WeatherUseCaseImpl
import com.example.features.weather.viewmodel.HoursWeatherUseCase
import com.example.features.weather.viewmodel.PreviewBarWeatherUseCase
import com.example.features.weather.viewmodel.WeatherUseCase
import com.example.features.weather.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel() }
    single<WeatherRepository> { WeatherRepositoryImpl() }
    single<NotesRepository> { NotesRepositoryImpl() }

    factory<WeatherUseCase> { WeatherUseCaseImpl(get()) }
    factory<HoursWeatherUseCase> { HoursWeatherUseCaseImpl(get()) }
    factory<PreviewBarWeatherUseCase> { PreviewBarWeatherUseCaseImpl(get()) }

    factory<GetNotesUseCase> { GetNotesUseCaseImpl(get()) }
    factory<AddNoteUseCase> { AddNoteUseCaseImpl(get()) }
    factory<DeleteNote> { DeleteNoteImpl(get()) }
    factory<UpdateNote> { UpdateNoteImpl(get()) }

    viewModel { WeatherViewModel(get(), get(), get()) }
    viewModel { NotesViewModel(get(), get(), get(), get()) }
}