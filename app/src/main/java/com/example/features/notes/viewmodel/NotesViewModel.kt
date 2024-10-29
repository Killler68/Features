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
    private val deleteNote: DeleteNote,
    private val updateNoteUseCase: UpdateNote
) : ViewModel() {

    private var _stateGetNotes = mutableStateOf<List<NotesModel>>(emptyList())
    val stateGetNotes: State<List<NotesModel>> get() = _stateGetNotes

    var isAddNote = mutableStateOf(false)
    var isEditing = mutableStateOf(false)

    init {
        loadNotes()
    }

    fun createNote(note: NotesModel) {
        addNote(note)
        loadNotes()
    }

    private fun loadNotes() {
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

    fun updateNote(id: Int, note: NotesModel) {
        viewModelScope.launch {
            updateNoteUseCase(id, note)
            loadNotes()
        }
    }
}