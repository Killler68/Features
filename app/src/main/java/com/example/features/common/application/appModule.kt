package com.example.features.common.application

import androidx.room.Room
import com.example.features.MainViewModel
import com.example.features.common.database.NotesDatabase
import com.example.features.common.database.user.UserDatabase
import com.example.features.common.repository.UserRepository
import com.example.features.common.repository.UserRepositoryImpl
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.features.repository.FeaturesRepositoryImpl
import com.example.features.features.usecase.FeaturesRepository
import com.example.features.features.usecase.FeaturesUseCaseImpl
import com.example.features.features.viewmodel.FeaturesUseCase
import com.example.features.features.viewmodel.FeaturesViewModel
import com.example.features.notes.repository.NotesRepositoryImpl
import com.example.features.notes.usecase.AddNoteUseCaseImpl
import com.example.features.notes.usecase.DeleteNoteImpl
import com.example.features.notes.usecase.GetNoteByIdUseCaseImpl
import com.example.features.notes.usecase.GetNotesUseCaseImpl
import com.example.features.notes.usecase.NotesRepository
import com.example.features.notes.usecase.UpdateNoteImpl
import com.example.features.notes.viewmodel.AddNoteUseCase
import com.example.features.notes.viewmodel.DeleteNote
import com.example.features.notes.viewmodel.GetNoteByIdUseCase
import com.example.features.notes.viewmodel.GetNotesUseCase
import com.example.features.notes.viewmodel.NotesViewModel
import com.example.features.notes.viewmodel.UpdateNote
import com.example.features.registration.usecase.CreateUserUseCaseImpl
import com.example.features.registration.viewmodel.CreateUserUseCase
import com.example.features.registration.viewmodel.RegistrationViewModel
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

    single { SharedViewModel() }

    single<FeaturesRepository> { FeaturesRepositoryImpl() }
    single<WeatherRepository> { WeatherRepositoryImpl() }
    single<NotesRepository> { NotesRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }

    single {
        Room.databaseBuilder(
            get(),
            NotesDatabase::class.java,
            "notes_database"
        ).build()
    }
    single { get<NotesDatabase>().notesDao() }

    single {
        Room.databaseBuilder(
            get(),
            UserDatabase::class.java,
            "user_database"
        ).build()
    }
    single { get<UserDatabase>().userDao() }

    factory<CreateUserUseCase> { CreateUserUseCaseImpl(get()) }
    factory<FeaturesUseCase> { FeaturesUseCaseImpl(get()) }

    factory<WeatherUseCase> { WeatherUseCaseImpl(get()) }
    factory<HoursWeatherUseCase> { HoursWeatherUseCaseImpl(get()) }
    factory<PreviewBarWeatherUseCase> { PreviewBarWeatherUseCaseImpl(get()) }

    factory<GetNotesUseCase> { GetNotesUseCaseImpl(get()) }
    factory<GetNoteByIdUseCase> { GetNoteByIdUseCaseImpl(get()) }
    factory<AddNoteUseCase> { AddNoteUseCaseImpl(get()) }
    factory<DeleteNote> { DeleteNoteImpl(get()) }
    factory<UpdateNote> { UpdateNoteImpl(get()) }

    viewModel { RegistrationViewModel(get(), get()) }
    viewModel { FeaturesViewModel(get()) }
    viewModel { WeatherViewModel(get(), get(), get()) }
    viewModel { NotesViewModel(get(), get(), get(), get(), get()) }
}