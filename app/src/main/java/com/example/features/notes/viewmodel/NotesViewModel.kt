package com.example.features.notes.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.notes.model.NotesModel
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotes: GetNotesUseCase,
    private val getNoteById: GetNoteByIdUseCase,
    private val addNote: AddNoteUseCase,
    private val deleteNote: DeleteNote,
    private val updateNoteUseCase: UpdateNote
) : ViewModel() {

    private var _stateGetNotes = mutableStateOf<List<NotesModel>>(emptyList())
    val stateGetNotes: State<List<NotesModel>> get() = _stateGetNotes

    var isAddNote = mutableStateOf(false)
    var isEditing = mutableStateOf(false)

    var editingNoteId = mutableStateOf<Int?>(null)

    init {
        loadNotes()
    }

    fun createNote(note: NotesModel) {
        viewModelScope.launch {
            addNote(note)
            loadNotes()
        }
    }

    private fun loadNotes() {
        viewModelScope.launch {
            _stateGetNotes.value = getNotes()
        }
    }

    fun getNoteDetail(noteId: Int): NotesModel? {
        return stateGetNotes.value.find { it.id == noteId }
    }

    fun removeNote(note: NotesModel) {
        viewModelScope.launch {
            deleteNote(note)
            loadNotes()
        }
    }

    fun updateNote(note: NotesModel) {
        viewModelScope.launch {
            updateNoteUseCase(note)
            loadNotes()
            editingNoteId.value = null
        }
    }
}