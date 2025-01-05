package com.example.features.notes.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.notes.model.NotesModel
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotes: GetNotesUseCase,
    private val addNote: AddNoteUseCase,
    private val deleteNote: DeleteNote,
    private val updateNoteUseCase: UpdateNote,
    private val sharedViewModel: SharedViewModel
) : ViewModel() {

    private var _stateGetNotes = mutableStateOf<List<NotesModel>>(emptyList())
    val stateGetNotes: State<List<NotesModel>> get() = _stateGetNotes

    var isAddNote = mutableStateOf(false)

    var editingNoteId = mutableStateOf<Int?>(null)

    init {
        loadNotes()
    }

    fun createNote(note: NotesModel) {
        viewModelScope.launch {
            val currentUser = sharedViewModel.currentUser.value
            currentUser?.let { user ->
                addNote(note.copy(userId = user.id))
            }
            loadNotes()
        }
    }

    private fun loadNotes() {
        viewModelScope.launch {
            val currentUser = sharedViewModel.currentUser.value
            currentUser?.let { user ->
                _stateGetNotes.value = getNotes(user.id)
            }
        }
    }

    fun getNoteDetail(noteId: Int): NotesModel? {
        return stateGetNotes.value.find { it.noteId == noteId }
    }

    fun removeNote(note: NotesModel) {
        viewModelScope.launch {
            val currentUser = sharedViewModel.currentUser.value
            currentUser?.let { user ->
                deleteNote(note.copy(userId = user.id))
            }
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