package com.example.features.notes.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.navigation.Screens
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.domain.usecase.DeleteNoteUseCase
import com.example.features.notes.domain.usecase.GetNoteByNoteIdUseCase
import com.example.features.notes.domain.usecase.UpdateNoteUseCase
import com.example.features.notes.presentation.models.NoteDetailedEvent
import com.example.features.notes.presentation.models.NoteDetailedSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NoteDetailedViewModel(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val getNoteByNoteIdUseCase: GetNoteByNoteIdUseCase
) : ViewModel() {

    private val _note = mutableStateOf<NoteTaskItem?>(null)
    val note: State<NoteTaskItem?> get() = _note

    private val _effect = MutableSharedFlow<NoteDetailedSideEffect>()
    val effect: SharedFlow<NoteDetailedSideEffect> get() = _effect.asSharedFlow()

    fun dispatch(event: NoteDetailedEvent) {
        when (event) {
            is NoteDetailedEvent.LoadNoteDetailed -> loadNote(event.userId, event.noteId)
            is NoteDetailedEvent.OnClickBack -> navigateTo(Screens.NotesTaskScreen.createRoute(event.userId))
            is NoteDetailedEvent.OnClickDelete -> deleteNote(event.userId, event.note)
            is NoteDetailedEvent.OnClickEditing -> updateNote(event.note)
            NoteDetailedEvent.OnClickCloseDialog -> clearSideEffect()
            NoteDetailedEvent.OnClickShowDialog -> showDialog()
        }
    }

    private fun loadNote(userId: Int, noteId: Int) {
        viewModelScope.launch {
            _note.value = getNoteByNoteIdUseCase(userId, noteId) ?: return@launch
        }
    }

    private fun deleteNote(userId: Int, note: NoteTaskItem?) {
        note?.let {
            viewModelScope.launch {
                deleteNoteUseCase(it.copy(userId = userId))
                _note.value = null
                clearSideEffect()
                navigateTo(Screens.NotesTaskScreen.createRoute(userId))
            }
        }
    }

    private fun updateNote(note: NoteTaskItem) {
        viewModelScope.launch {
            updateNoteUseCase(note)
            _note.value = note
            clearSideEffect()
        }
    }

    private fun navigateTo(route: String) {
        viewModelScope.launch {
            _effect.emit(NoteDetailedSideEffect.NavigateTo(route))
        }
    }

    private fun showDialog() {
        viewModelScope.launch {
            _effect.emit(NoteDetailedSideEffect.Popup)
        }
    }

    private fun clearSideEffect() {
        viewModelScope.launch {
            _effect.emit(NoteDetailedSideEffect.None)
        }
    }
}