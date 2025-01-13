package com.example.features.notes.noteslist.viewmodel

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

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val sharedViewModel: SharedViewModel
) : ViewModel() {

    private var _stateGetNotes = mutableStateOf<List<NotesModel>>(emptyList())
    val stateGetNotes: State<List<NotesModel>> get() = _stateGetNotes

    var isAddNote = mutableStateOf(false)
    var isChoiceNote = mutableStateOf(false)

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