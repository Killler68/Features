package com.example.features.common.application

import androidx.room.Room
import com.example.features.MainViewModel
import com.example.features.authorization.GetUserByLoginAndPassword
import com.example.features.authorization.GetUserByLoginAndPasswordImpl
import com.example.features.common.database.notes.NotesDatabase
import com.example.features.common.database.profile.UserAdditionalInfoDatabase
import com.example.features.common.database.user.UserDatabase
import com.example.features.common.repository.UserRepository
import com.example.features.common.repository.UserRepositoryImpl
import com.example.features.common.repository.profile.UserAdditionalInfoRepository
import com.example.features.common.repository.profile.UserAdditionalInfoRepositoryImpl
import com.example.features.common.sharedpreferences.LocalStorage
import com.example.features.common.sharedpreferences.LocalStorageImpl
import com.example.features.common.usecase.CheckLocaleUseCase
import com.example.features.common.usecase.CheckLocaleUseCaseImpl
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.features.repository.FeaturesRepositoryImpl
import com.example.features.features.usecase.FeaturesRepository
import com.example.features.features.usecase.FeaturesUseCaseImpl
import com.example.features.features.usecase.GetDrawerItemsImpl
import com.example.features.features.viewmodel.FeaturesUseCase
import com.example.features.features.viewmodel.FeaturesViewModel
import com.example.features.features.viewmodel.GetDrawerItems
import com.example.features.notes.common.repository.NotesRepositoryImpl
import com.example.features.notes.common.usecase.DeleteNoteUseCase
import com.example.features.notes.common.usecase.GetNotesUseCase
import com.example.features.notes.common.usecase.NotesRepository
import com.example.features.notes.common.usecase.UpdateNoteUseCase
import com.example.features.notes.noteadd.usecase.AddNoteUseCase
import com.example.features.notes.noteadd.viewmodel.NoteAddViewModel
import com.example.features.notes.notedetail.viewmodel.NoteDetailViewModel
import com.example.features.notes.noteslist.viewmodel.NotesViewModel
import com.example.features.profile.usecase.CreateUserAdditionalInfoUseCaseImpl
import com.example.features.profile.usecase.GetUserAdditionalInfoByIdUseCaseImpl
import com.example.features.profile.usecase.UpdateUserAdditionalInfoUseCaseImpl
import com.example.features.profile.viewmodel.CreateUserAdditionalInfoUseCase
import com.example.features.profile.viewmodel.GetUserAdditionalInfoByIdUseCase
import com.example.features.profile.viewmodel.UpdateUserAdditionalInfoUseCase
import com.example.features.profile.viewmodel.UserAdditionalInfoViewModel
import com.example.features.registration.usecase.CreateUserUseCaseImpl
import com.example.features.registration.viewmodel.CreateUserUseCase
import com.example.features.registration.viewmodel.RegistrationViewModel
import com.example.features.settings.usecase.DeleteUserUseCase
import com.example.features.settings.viewmodel.SettingsViewModel
import com.example.features.weather.detailed.usecase.WeatherAdditionalInfoDayUseCase
import com.example.features.weather.detailed.usecase.WeatherDetailedDayUseCase
import com.example.features.weather.detailed.usecase.WeatherDetailedRepository
import com.example.features.weather.detailed.usecase.WeatherHoursDayUseCase
import com.example.features.weather.detailed.viewmodel.WeatherDetailedViewModel
import com.example.features.weather.repository.WeatherRepositoryImpl
import com.example.features.weather.usecase.HoursWeatherUseCaseImpl
import com.example.features.weather.usecase.PreviewBarWeatherUseCaseImpl
import com.example.features.weather.usecase.WeatherRepository
import com.example.features.weather.usecase.WeatherUseCaseImpl
import com.example.features.weather.viewmodel.HoursWeatherUseCase
import com.example.features.weather.viewmodel.PreviewBarWeatherUseCase
import com.example.features.weather.viewmodel.WeatherUseCase
import com.example.features.weather.viewmodel.WeatherViewModel
import com.example.features.welcome.repository.WelcomeRepositoryImpl
import com.example.features.welcome.usecase.WelcomeRepository
import com.example.features.welcome.usecase.WelcomeUseCaseImpl
import com.example.features.welcome.viewmodel.WelcomeUseCase
import com.example.features.welcome.viewmodel.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<CheckLocaleUseCase> { CheckLocaleUseCaseImpl(get()) }
    single { SharedViewModel(get()) }

    single<WelcomeRepository> { WelcomeRepositoryImpl() }
    single<FeaturesRepository> { FeaturesRepositoryImpl() }
    single<WeatherRepository> { WeatherRepositoryImpl() }
    single<WeatherDetailedRepository> { WeatherRepositoryImpl() }
    single<NotesRepository> { NotesRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<UserAdditionalInfoRepository> { UserAdditionalInfoRepositoryImpl(get()) }

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

    single {
        Room.databaseBuilder(
            get(),
            UserAdditionalInfoDatabase::class.java,
            "user_additional_info_database"
        ).build()
    }

    factory<LocalStorage> { LocalStorageImpl(get()) }

    single { get<UserAdditionalInfoDatabase>().userAdditionalInfoDao() }
    single { get<UserDatabase>().userDao() }


    factory<WelcomeUseCase> { WelcomeUseCaseImpl(get()) }

    factory<CreateUserUseCase> { CreateUserUseCaseImpl(get()) }
    factory<FeaturesUseCase> { FeaturesUseCaseImpl(get()) }
    factory<GetDrawerItems> { GetDrawerItemsImpl(get()) }
    factory<GetUserByLoginAndPassword> { GetUserByLoginAndPasswordImpl(get()) }

    factory<WeatherUseCase> { WeatherUseCaseImpl(get()) }
    factory<HoursWeatherUseCase> { HoursWeatherUseCaseImpl(get()) }
    factory<PreviewBarWeatherUseCase> { PreviewBarWeatherUseCaseImpl(get()) }

    factory { WeatherDetailedDayUseCase(get()) }
    factory { WeatherAdditionalInfoDayUseCase(get()) }
    factory { WeatherHoursDayUseCase(get()) }

    factory<CreateUserAdditionalInfoUseCase> { CreateUserAdditionalInfoUseCaseImpl(get()) }
    factory<GetUserAdditionalInfoByIdUseCase> { GetUserAdditionalInfoByIdUseCaseImpl(get()) }
    factory<UpdateUserAdditionalInfoUseCase> { UpdateUserAdditionalInfoUseCaseImpl(get()) }

    factory { DeleteUserUseCase(get()) }

    factory { GetNotesUseCase(get()) }
    factory { AddNoteUseCase(get()) }
    factory { DeleteNoteUseCase(get()) }
    factory { UpdateNoteUseCase(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { WelcomeViewModel(get()) }
    viewModel { RegistrationViewModel(get(), get()) }
    viewModel { FeaturesViewModel(get(), get()) }
    viewModel { WeatherViewModel(get(), get(), get()) }
    viewModel { NotesViewModel(get(), get(), get(), get()) }
    viewModel { UserAdditionalInfoViewModel(get(), get(), get()) }
    viewModel { NoteAddViewModel(get(), get()) }
    viewModel { NoteDetailViewModel(get(), get(), get(), get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { WeatherDetailedViewModel(get(), get(), get()) }
}