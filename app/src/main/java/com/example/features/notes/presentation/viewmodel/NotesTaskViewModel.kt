package com.example.features.notes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.navigation.Screens
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.domain.usecase.DeleteTaskUseCase
import com.example.features.notes.domain.usecase.GetNotesUseCase
import com.example.features.notes.domain.usecase.GetTasksUseCase
import com.example.features.notes.domain.usecase.UpdateTaskUseCase
import com.example.features.notes.presentation.models.NotesTaskEvent
import com.example.features.notes.presentation.models.NotesTaskSideEffect
import com.example.features.notes.presentation.models.NotesTaskState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesTaskViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteTaskUseCase,
    private val updateNoteUseCase: UpdateTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NotesTaskState())
    val state: StateFlow<NotesTaskState> get() = _state.asStateFlow()

    private val _effect = MutableSharedFlow<NotesTaskSideEffect>()
    val effect: SharedFlow<NotesTaskSideEffect> get() = _effect.asSharedFlow()

    fun dispatch(event: NotesTaskEvent) =
        viewModelScope.launch {
            when (event) {
                is NotesTaskEvent.LoadNotes -> loadNotes(event.userId)
                is NotesTaskEvent.LoadTask -> loadTask(event.userId)
                is NotesTaskEvent.OnClickBack -> navigateTo(Screens.Features.createRoute(event.userId))
                is NotesTaskEvent.OnClickDeleteNote -> deleteNote(event.userId, event.note)
                is NotesTaskEvent.OnClickUpdateNote -> updateNotesTask(event.userId, event.note)
                is NotesTaskEvent.OnClickEditNote -> editNote(event.note)
                is NotesTaskEvent.OnClickNoteDetailed -> navigateTo(
                    Screens.NotesDetail.createRouter(event.userId, event.noteId)
                )

                is NotesTaskEvent.OnClickAddNote -> navigateTo(
                    Screens.NoteAddScreen.createRoute(event.userId)
                )

                is NotesTaskEvent.OnClickAddTask -> navigateTo(
                    Screens.TaskAddScreen.createRoute(event.userId)
                )

                NotesTaskEvent.OnClickDialog -> showDialog()
                NotesTaskEvent.OnCloseDialog -> clearSideEffects()
            }
        }

    private fun loadNotes(userId: Int) =
        viewModelScope.launch {
            val notes = getNotesUseCase(userId)
            _state.update { it.copy(notes = notes) }
        }

    private fun loadTask(userId: Int) =
        viewModelScope.launch {
            val tasks = getTasksUseCase(userId)
            _state.update { it.copy(tasks = tasks) }
        }

    private fun deleteNote(userId: Int, note: NoteTaskItem) =
        viewModelScope.launch {
            deleteNoteUseCase(note.copy(userId = userId))
            loadNotes(userId)
        }

    private fun updateNotesTask(userId: Int, note: NoteTaskItem) =
        viewModelScope.launch {
            updateNoteUseCase(note)
            _state.update {
                it.copy(
                    notes = getNotesUseCase(userId),
                    tasks = getTasksUseCase(userId),
                    editingNote = null
                )
            }
        }

    private fun editNote(note: NoteTaskItem?) {
        _state.update { it.copy(editingNote = note) }
    }

    private fun navigateTo(route: String) =
        viewModelScope.launch {
            _effect.emit(NotesTaskSideEffect.NavigateTo(route))
        }

    private fun showDialog() =
        viewModelScope.launch {
            _effect.emit(NotesTaskSideEffect.Popup)
        }

    private fun clearSideEffects() =
        viewModelScope.launch {
            _effect.emit(NotesTaskSideEffect.None)
        }
}