package com.example.features.notes.notedetail.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.notes.common.model.NotesModel
import com.example.features.notes.common.usecase.DeleteNoteUseCase
import com.example.features.notes.common.usecase.GetNotesUseCase
import com.example.features.notes.common.usecase.UpdateNoteUseCase
import kotlinx.coroutines.launch

class NoteDetailViewModel(
    private val sharedViewModel: SharedViewModel,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {

    private var _stateGetNotes = mutableStateOf<List<NotesModel>>(emptyList())
    private val stateGetNotes: State<List<NotesModel>> get() = _stateGetNotes

    var editingNoteId = mutableStateOf<Int?>(null)

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            val currentUser = sharedViewModel.currentUser.value
            currentUser?.let { user ->
                _stateGetNotes.value = getNotesUseCase(user.id)
            }
        }
    }

    fun getNoteDetail(noteId: Int): NotesModel? {
        return stateGetNotes.value.find { it.noteId == noteId }
    }

    fun deleteNote(note: NotesModel) {
        viewModelScope.launch {
            val currentUser = sharedViewModel.currentUser.value
            currentUser?.let { user ->
                deleteNoteUseCase(note.copy(userId = user.id))
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