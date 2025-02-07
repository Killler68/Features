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
import com.example.features.notes.noteslist.usecase.DeleteTaskUseCase
import com.example.features.notes.noteslist.usecase.GetTasksUseCase
import com.example.features.notes.noteslist.usecase.UpdateTaskUseCase
import com.example.features.notes.task.model.TaskModel
import kotlinx.coroutines.launch

data class GridItem(
    val id: Int,
    val type: ItemType,
    val title: String? = null,
    val description: String? = null,
    val userId: Int,
    val isComplete: Boolean = false
)

enum class ItemType {
    TASK, NOTE
}

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val sharedViewModel: SharedViewModel,
    private val getTasksUseCase: GetTasksUseCase,
) : ViewModel() {

    private var _stateGetNotes = mutableStateOf<List<NotesModel>>(emptyList())
    val stateGetNotes: State<List<NotesModel>> get() = _stateGetNotes

    private var _getTask = mutableStateOf<List<TaskModel>>(emptyList())
    val getTask: State<List<TaskModel>> get() = _getTask

    var isAddNote = mutableStateOf(false)
    var isChoiceNote = mutableStateOf(false)

    var editingNoteId = mutableStateOf<Int?>(null)

    init {
        loadNotes()
        loadTask()
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

    private fun loadTask() {
        viewModelScope.launch {
            val currentUser = sharedViewModel.currentUser.value
            currentUser?.let { user ->
                _getTask.value = getTasksUseCase(user.id)
            }
        }
    }

    val combinedList: List<GridItem>
        get() = (_getTask.value.map {
            GridItem(it.taskId, ItemType.TASK, it.taskText, "", it.userId, it.isComplete)
        } + _stateGetNotes.value.map {
            GridItem(it.noteId, ItemType.NOTE, it.title, it.description, it.noteId, false)
        }).sortedBy { it.id }
}