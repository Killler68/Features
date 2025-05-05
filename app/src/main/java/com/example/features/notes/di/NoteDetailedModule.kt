package com.example.features.notes.di

import com.example.features.notes.domain.usecase.GetNoteByNoteIdUseCase
import com.example.features.notes.presentation.viewmodel.NoteDetailedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object NoteDetailedModule {

    val module = module {
        factory { GetNoteByNoteIdUseCase(get()) }
        viewModel { NoteDetailedViewModel(get(), get(), get()) }
    }
}