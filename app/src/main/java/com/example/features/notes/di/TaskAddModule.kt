package com.example.features.notes.di

import com.example.features.notes.domain.usecase.AddTaskUseCase
import com.example.features.notes.presentation.viewmodel.TaskAddViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object TaskAddModule {
    val module = module {
        factory { AddTaskUseCase(get()) }
        viewModel { TaskAddViewModel(get()) }
    }
}