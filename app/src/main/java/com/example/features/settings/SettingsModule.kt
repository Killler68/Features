package com.example.features.settings

import com.example.features.settings.usecase.DeleteUserUseCase
import com.example.features.settings.usecase.GetUserByIdUseCase
import com.example.features.settings.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SettingsModule {
    val module = module {
        factory { DeleteUserUseCase(get()) }
        factory { GetUserByIdUseCase(get()) }
        viewModel { SettingsViewModel(get()) }
    }
}