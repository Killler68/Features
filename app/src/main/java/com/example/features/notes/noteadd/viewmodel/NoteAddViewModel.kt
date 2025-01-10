package com.example.features.notes.noteadd.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.notes.common.model.NotesModel
import com.example.features.notes.noteadd.usecase.AddNoteUseCase
import kotlinx.coroutines.launch

class NoteAddViewModel(
    private val sharedViewModel: SharedViewModel,
    private val addNoteUseCase: AddNoteUseCase,
) : ViewModel() {

    fun createNote(note: NotesModel) {
        viewModelScope.launch {
            val currentUser = sharedViewModel.currentUser.value
            currentUser?.let { user ->
                addNoteUseCase(note.copy(userId = user.id))
            }
        }
    }
}