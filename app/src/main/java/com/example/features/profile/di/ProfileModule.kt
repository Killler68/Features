package com.example.features.profile.di

import androidx.room.Room
import com.example.features.profile.data.database.ProfileDatabase
import com.example.features.profile.data.repository.ProfileRepository
import com.example.features.profile.data.repository.ProfileRepositoryImpl
import com.example.features.profile.domain.usecase.CreateProfileUseCase
import com.example.features.profile.domain.usecase.GetProfileByIdUseCase
import com.example.features.profile.domain.usecase.UpdateProfileUseCase
import com.example.features.profile.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ProfileModule {
    val module = module {
        factory { CreateProfileUseCase(get()) }
        factory { GetProfileByIdUseCase(get()) }
        factory { UpdateProfileUseCase(get()) }
        viewModel { ProfileViewModel(get(), get(), get()) }

        single<ProfileRepository> { ProfileRepositoryImpl(get()) }

        single { get<ProfileDatabase>().profileDao() }
        single {
            Room.databaseBuilder(
                get(),
                ProfileDatabase::class.java,
                "profile_database"
            ).build()
        }
    }
}