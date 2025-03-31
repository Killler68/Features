package com.example.features.notes.di

import com.example.features.notes.domain.usecase.AddNoteUseCase
import com.example.features.notes.presentation.viewmodel.NoteAddViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object NoteAddModule {

    val module = module {
        factory { AddNoteUseCase(get()) }
        viewModel { NoteAddViewModel(get()) }
    }
}