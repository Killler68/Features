package com.example.features.notes.di

import androidx.room.Room
import com.example.features.notes.data.database.NotesTaskDatabase
import com.example.features.notes.data.repository.NotesTaskRepository
import com.example.features.notes.data.repository.NotesTaskRepositoryImpl
import com.example.features.notes.domain.usecase.DeleteNoteUseCase
import com.example.features.notes.domain.usecase.DeleteTaskUseCase
import com.example.features.notes.domain.usecase.GetNotesUseCase
import com.example.features.notes.domain.usecase.GetTasksUseCase
import com.example.features.notes.domain.usecase.UpdateNoteUseCase
import com.example.features.notes.domain.usecase.UpdateTaskUseCase
import com.example.features.notes.presentation.viewmodel.NotesTaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object NotesTaskModule {
    val module = module {
        factory { GetNotesUseCase(get()) }
        factory { UpdateNoteUseCase(get()) }
        factory { DeleteNoteUseCase(get()) }

        factory { GetTasksUseCase(get()) }
        factory { UpdateTaskUseCase(get()) }
        factory { DeleteTaskUseCase(get()) }

        viewModel { NotesTaskViewModel(get(), get(), get(), get()) }

        single<NotesTaskRepository> { NotesTaskRepositoryImpl(get()) }

        single {
            Room.databaseBuilder(
                get(),
                NotesTaskDatabase::class.java,
                "task_database"
            ).build()
        }
        single { get<NotesTaskDatabase>().taskDao() }
    }
}