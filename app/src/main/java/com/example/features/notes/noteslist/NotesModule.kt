package com.example.features.notes.noteslist

import com.example.features.notes.common.repository.NotesRepositoryImpl
import com.example.features.notes.common.usecase.DeleteNoteUseCase
import com.example.features.notes.common.usecase.GetNotesUseCase
import com.example.features.notes.common.usecase.NotesRepository
import com.example.features.notes.common.usecase.UpdateNoteUseCase
import com.example.features.notes.noteadd.usecase.AddNoteUseCase
import com.example.features.notes.notedetail.viewmodel.NoteDetailViewModel
import com.example.features.notes.noteslist.usecase.DeleteTaskUseCase
import com.example.features.notes.noteslist.usecase.GetTasksUseCase
import com.example.features.notes.noteslist.usecase.UpdateTaskUseCase
import com.example.features.notes.noteslist.viewmodel.NotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object NotesModule {
    val module = module {
        single<NotesRepository> { NotesRepositoryImpl(get()) }
        factory { DeleteNoteUseCase(get()) }
        factory { GetNotesUseCase(get()) }
        factory { UpdateNoteUseCase(get()) }
        factory { DeleteTaskUseCase(get()) }
        factory { UpdateTaskUseCase(get()) }
        factory { GetTasksUseCase(get()) }
        factory { AddNoteUseCase(get()) }
        viewModel { NotesViewModel(get(), get(), get(), get(), get()) }
        viewModel { NoteDetailViewModel(get(), get(), get(), get()) }
    }
}