package com.example.features.common.application

import androidx.room.Room
import com.example.features.MainViewModel
import com.example.features.authorization.GetUserByLoginAndPassword
import com.example.features.authorization.GetUserByLoginAndPasswordImpl
import com.example.features.authorization.viewmodel.AuthorizationViewModel
import com.example.features.common.database.notes.NotesDatabase
import com.example.features.common.database.profile.ProfileDatabase
import com.example.features.common.database.task.TaskDatabase
import com.example.features.common.database.user.UserDatabase
import com.example.features.common.repository.UserRepository
import com.example.features.common.repository.UserRepositoryImpl
import com.example.features.common.repository.profile.ProfileRepository
import com.example.features.common.repository.profile.ProfileRepositoryImpl
import com.example.features.common.repository.task.TaskRepository
import com.example.features.common.repository.task.TaskRepositoryImpl
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
import com.example.features.notes.noteslist.usecase.DeleteTaskUseCase
import com.example.features.notes.noteslist.usecase.GetTasksUseCase
import com.example.features.notes.noteslist.usecase.UpdateTaskUseCase
import com.example.features.notes.noteslist.viewmodel.NotesViewModel
import com.example.features.notes.task.usecase.CreateTaskUseCase
import com.example.features.notes.task.viewmodel.TaskViewModel
import com.example.features.profile.usecase.CreateProfileUseCaseImpl
import com.example.features.profile.usecase.GetProfileByIdUseCaseImpl
import com.example.features.profile.usecase.UpdateProfileUseCaseImpl
import com.example.features.profile.viewmodel.CreateProfileUseCase
import com.example.features.profile.viewmodel.GetProfileByIdUseCase
import com.example.features.profile.viewmodel.UpdateProfileUseCase
import com.example.features.profile.viewmodel.ProfileViewModel
import com.example.features.registration.usecase.CreateUserUseCaseImpl
import com.example.features.registration.usecase.GetUserByLoginUseCase
import com.example.features.registration.viewmodel.CreateUserUseCase
import com.example.features.registration.viewmodel.RegistrationViewModel
import com.example.features.settings.usecase.DeleteUserUseCase
import com.example.features.settings.viewmodel.SettingsViewModel
import com.example.features.weather.detailed.usecase.ItemTemperatureUseCase
import com.example.features.weather.detailed.usecase.WeatherDetailedDayUseCase
import com.example.features.weather.detailed.usecase.WeatherDetailedRepository
import com.example.features.weather.detailed.usecase.WeatherHoursDayUseCase
import com.example.features.weather.detailed.viewmodel.WeatherDetailedViewModel
import com.example.features.weather.repository.WeatherRepositoryImpl
import com.example.features.weather.usecase.PreviewBarWeatherUseCaseImpl
import com.example.features.weather.usecase.WeatherRepository
import com.example.features.weather.usecase.WeatherUseCaseImpl
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
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    single<TaskRepository> { TaskRepositoryImpl(get()) }

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
            TaskDatabase::class.java,
            "task_database"
        ).build()
    }
    single { get<TaskDatabase>().taskDao() }

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
            ProfileDatabase::class.java,
            "profile_database"
        ).build()
    }

    factory<LocalStorage> { LocalStorageImpl(get()) }

    single { get<ProfileDatabase>().profileDao() }
    single { get<UserDatabase>().userDao() }


    factory<WelcomeUseCase> { WelcomeUseCaseImpl(get()) }

    factory<CreateUserUseCase> { CreateUserUseCaseImpl(get()) }
    factory<FeaturesUseCase> { FeaturesUseCaseImpl(get()) }
    factory<GetDrawerItems> { GetDrawerItemsImpl(get()) }
    factory<GetUserByLoginAndPassword> { GetUserByLoginAndPasswordImpl(get()) }

    factory<WeatherUseCase> { WeatherUseCaseImpl(get()) }
    factory<PreviewBarWeatherUseCase> { PreviewBarWeatherUseCaseImpl(get()) }
    factory { ItemTemperatureUseCase() }

    factory { WeatherDetailedDayUseCase(get()) }
    factory { WeatherHoursDayUseCase(get()) }

    factory<CreateProfileUseCase> { CreateProfileUseCaseImpl(get()) }
    factory<GetProfileByIdUseCase> { GetProfileByIdUseCaseImpl(get()) }
    factory<UpdateProfileUseCase> { UpdateProfileUseCaseImpl(get()) }

    factory { DeleteUserUseCase(get()) }

    factory { GetNotesUseCase(get()) }
    factory { AddNoteUseCase(get()) }
    factory { DeleteNoteUseCase(get()) }
    factory { UpdateNoteUseCase(get()) }

    factory { GetTasksUseCase(get()) }
    factory { DeleteTaskUseCase(get()) }
    factory { UpdateTaskUseCase(get()) }
    factory { CreateTaskUseCase(get()) }
    factory { GetUserByLoginUseCase(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { WelcomeViewModel(get()) }
    viewModel { RegistrationViewModel(get(), get(), get()) }
    viewModel { AuthorizationViewModel(get()) }
    viewModel { FeaturesViewModel(get(), get(), get(), get()) }
    viewModel { WeatherViewModel(get(), get()) }
    viewModel { NotesViewModel(get(), get(), get(), get(), get()) }
    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { NoteAddViewModel(get(), get()) }
    viewModel { NoteDetailViewModel(get(), get(), get(), get()) }
    viewModel { TaskViewModel(get(), get()) }
    viewModel { SettingsViewModel(get(), get()) }
    viewModel { WeatherDetailedViewModel(get(), get(), get()) }
}