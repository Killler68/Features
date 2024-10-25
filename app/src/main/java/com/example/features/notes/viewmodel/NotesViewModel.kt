package com.example.features.notes.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.notes.model.NotesModel
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotes: GetNotesUseCase,
    private val addNote: AddNoteUseCase,
    private val deleteNote: DeleteNote
) : ViewModel() {

    private val _stateGetNotes = mutableStateOf<List<NotesModel>>(emptyList())
    val stateGetNotes: State<List<NotesModel>> = _stateGetNotes

    fun createNote(note: NotesModel) {
        addNote(note)
    }

    fun loadNotes() {
        viewModelScope.launch {
            _stateGetNotes.value = getNotes()
        }
    }

    fun removeNote(note: NotesModel) {
        viewModelScope.launch {
            deleteNote(note)
            _stateGetNotes.value = getNotes()
        }
    }
}