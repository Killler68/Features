package com.example.features.profile

import com.example.features.profile.usecase.CreateProfileUseCase
import com.example.features.profile.usecase.GetProfileByIdUseCase
import com.example.features.profile.usecase.UpdateProfileUseCase
import com.example.features.profile.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ProfileModule {
    val module = module {
        factory { CreateProfileUseCase(get()) }
        factory { GetProfileByIdUseCase(get()) }
        factory { UpdateProfileUseCase(get()) }
        viewModel { ProfileViewModel(get(), get(), get()) }
    }
}