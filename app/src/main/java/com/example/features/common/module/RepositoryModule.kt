package com.example.features.common.module

import com.example.features.common.repository.UserRepository
import com.example.features.common.repository.UserRepositoryImpl
import com.example.features.common.repository.profile.ProfileRepository
import com.example.features.common.repository.profile.ProfileRepositoryImpl
import com.example.features.common.repository.task.TaskRepository
import com.example.features.common.repository.task.TaskRepositoryImpl
import com.example.features.notes.common.repository.NotesRepositoryImpl
import com.example.features.notes.common.usecase.NotesRepository
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        single<NotesRepository> { NotesRepositoryImpl(get()) }
        single<UserRepository> { UserRepositoryImpl(get()) }
        single<ProfileRepository> { ProfileRepositoryImpl(get()) }
        single<TaskRepository> { TaskRepositoryImpl(get()) }
    }
}