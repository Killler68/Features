package com.example.features.notes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.navigation.Screens
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.domain.usecase.DeleteNotesTaskUseCase
import com.example.features.notes.domain.usecase.GetNotesUseCase
import com.example.features.notes.domain.usecase.GetTasksUseCase
import com.example.features.notes.domain.usecase.SelectColorBackgroundTaskUseCase
import com.example.features.notes.domain.usecase.UpdateNotesTaskUseCase
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
    private val deleteNoteTaskUseCase: DeleteNotesTaskUseCase,
    private val updateNoteTaskUseCase: UpdateNotesTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val selectColorUseCase: SelectColorBackgroundTaskUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NotesTaskState())
    val state: StateFlow<NotesTaskState> get() = _state.asStateFlow()

    private val _effect = MutableSharedFlow<NotesTaskSideEffect>()
    val effect: SharedFlow<NotesTaskSideEffect> get() = _effect.asSharedFlow()

    private var selectedNoteForDeletion: NoteTaskItem? = null

    fun dispatch(event: NotesTaskEvent) =
        viewModelScope.launch {
            when (event) {
                is NotesTaskEvent.LoadNotes -> loadNotes(event.userId)
                is NotesTaskEvent.LoadTask -> loadTask(event.userId)
                is NotesTaskEvent.OnClickBack -> navigateTo(Screens.Features.createRoute(event.userId))
                is NotesTaskEvent.OnClickDeleteNote -> deleteNote(event.userId)
                is NotesTaskEvent.OnClickDeleteTask -> deleteTask(event.userId)
                is NotesTaskEvent.OnClickUpdateNote -> updateNotesTask(event.userId, event.note)
                is NotesTaskEvent.OnClickEditNotesTask -> editNote(event.note)
                is NotesTaskEvent.OnSelectNoteForDeletion -> selectNoteForDeletion(event.note)

                is NotesTaskEvent.OnClickNoteDetailed -> navigateTo(
                    Screens.NotesDetail.createRouter(event.userId, event.noteId)
                )

                is NotesTaskEvent.OnClickAddNote -> navigateTo(
                    Screens.NoteAddScreen.createRoute(event.userId)
                )

                is NotesTaskEvent.OnClickAddTask -> navigateTo(
                    Screens.TaskAddScreen.createRoute(event.userId)
                )

                NotesTaskEvent.OnClickDialog -> {
                    _state.update { it.copy(isChoiceDialogVisible = true) }
                }

                NotesTaskEvent.OnCloseDialog -> {
                    _state.update { it.copy(isChoiceDialogVisible = false) }
                }

                NotesTaskEvent.OnOpenConfirmationNoteDialog -> {
                    _state.update { it.copy(isConfirmationDialogVisible = true) }
                }

                NotesTaskEvent.OnOpenConfirmationTaskDialog -> {
                    _state.update { it.copy(isConfirmationTaskDialogVisible = true) }
                }

                NotesTaskEvent.OnCloseConfirmationNoteDialog -> {
                    _state.update { it.copy(isConfirmationDialogVisible = false) }
                }

                NotesTaskEvent.OnCloseConfirmationTaskDialog -> {
                    _state.update { it.copy(isConfirmationTaskDialogVisible = false) }
                }

                is NotesTaskEvent.OnConfirmColor -> {
                    val old = _state.value.focusedTask ?: return@launch
                    val updated = old.copy(backgroundColor = event.colorArgb)
                    updateNoteTaskUseCase(updated)
                    val newNotes = _state.value.notes.map {
                        if (it.id == updated.id) updated else it
                    }
                    val newTasks = _state.value.tasks.map {
                        if (it.id == updated.id) updated else it
                    }

                    _state.update {
                        it.copy(
                            notes = newNotes,
                            tasks = newTasks,
                            focusedTask = null,
                            isColorDialogVisible = false
                        )
                    }
                }

                NotesTaskEvent.OnOpenColorDialog -> {
                    val cols = selectColorUseCase()
                    _state.update {
                        it.copy(
                            availableColors = cols,
                            isColorDialogVisible = true
                        )
                    }
                }

                NotesTaskEvent.OnCloseColorDialog -> {
                    _state.update { it.copy(isColorDialogVisible = false) }
                }

                is NotesTaskEvent.OnSelectTaskForColor -> {
                    loadColors()
                    _state.update {
                        it.copy(
                            isColorDialogVisible = true,
                            focusedTask = event.task
                        )
                    }
                }
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

    private fun deleteNote(userId: Int) =
        viewModelScope.launch {
            selectedNoteForDeletion?.let { note ->
                deleteNoteTaskUseCase(note.copy(userId = userId))
                selectedNoteForDeletion = null
                loadNotes(userId)
                _state.update { it.copy(isConfirmationDialogVisible = false) }
            }
        }

    private fun deleteTask(userId: Int) =
        viewModelScope.launch {
            selectedNoteForDeletion?.let { task ->
                deleteNoteTaskUseCase(task.copy(userId = userId))
                selectedNoteForDeletion = null
                val updatedTasks = getTasksUseCase(userId)
                _state.update {
                    it.copy(
                        tasks = updatedTasks,
                        isConfirmationTaskDialogVisible = false
                    )
                }
            }
        }

    private fun updateNotesTask(userId: Int, note: NoteTaskItem) =
        viewModelScope.launch {
            updateNoteTaskUseCase(note)
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

    private fun selectNoteForDeletion(note: NoteTaskItem) {
        selectedNoteForDeletion = note
    }

    private suspend fun loadColors() {
        val colors = selectColorUseCase()
        _state.update { it.copy(availableColors = colors) }
    }

    private fun navigateTo(route: String) =
        viewModelScope.launch {
            _effect.emit(NotesTaskSideEffect.NavigateTo(route))
        }
}