package com.example.features.settings.di

import com.example.features.settings.domain.DeleteUserUseCase
import com.example.features.settings.domain.GetUserByIdUseCase
import com.example.features.settings.presentation.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SettingsModule {
    val module = module {
        factory { DeleteUserUseCase(get()) }
        factory { GetUserByIdUseCase(get()) }
        viewModel { SettingsViewModel(get()) }
    }
}