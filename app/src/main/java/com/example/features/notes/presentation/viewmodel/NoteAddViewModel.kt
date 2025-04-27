package com.example.features.notes.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.navigation.Screens
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.domain.entities.TypeItem
import com.example.features.notes.domain.usecase.AddNoteUseCase
import com.example.features.notes.presentation.models.NoteAddEvent
import com.example.features.notes.presentation.models.NoteAddSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NoteAddViewModel(
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    private val _effect = MutableSharedFlow<NoteAddSideEffect>()
    val effect: SharedFlow<NoteAddSideEffect> get() = _effect.asSharedFlow()

    private var _editingNote = mutableStateOf<NoteTaskItem?>(null)
    val editingNote: State<NoteTaskItem?> get() = _editingNote

    fun dispatch(event: NoteAddEvent) {
        when (event) {
            is NoteAddEvent.OnClickBack -> navigateTo(Screens.NotesTaskScreen.createRoute(event.userId))
            is NoteAddEvent.CreateNote -> createNote(event.userId, event.title, event.description)
        }
    }

    private fun createNote(userId: Int, title: String, description: String) {
        viewModelScope.launch {
            val note = _editingNote.value?.copy(title = title, description = description)
                ?: NoteTaskItem(
                    id = 0,
                    type = TypeItem.NOTE,
                    title = title,
                    description = description,
                    userId = userId,
                    isComplete = false,
                    createTime = 0L
                )
            addNoteUseCase(note)
            navigateTo(Screens.NotesTaskScreen.createRoute(userId))
        }
    }

    private fun navigateTo(route: String) {
        viewModelScope.launch {
            _effect.emit(NoteAddSideEffect.NavigateTo(route))
        }
    }
}
