package com.example.features.notes.task

import com.example.features.notes.task.usecase.CreateTaskUseCase
import com.example.features.notes.task.viewmodel.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object TaskModule {
    val module = module {
        factory { CreateTaskUseCase(get()) }
        viewModel { TaskViewModel(get(), get()) }
    }
}