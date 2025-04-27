package com.example.features.notes.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.navigation.Screens
import com.example.features.notes.domain.entities.NoteTaskItem
import com.example.features.notes.domain.entities.TypeItem
import com.example.features.notes.domain.usecase.AddTaskUseCase
import com.example.features.notes.presentation.models.TaskAddEvent
import com.example.features.notes.presentation.models.TaskAddSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class TaskAddViewModel(
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {

    private val _effect = MutableSharedFlow<TaskAddSideEffect>()
    val effect: SharedFlow<TaskAddSideEffect> get() = _effect.asSharedFlow()

    private var _editingTask = mutableStateOf<NoteTaskItem?>(null)
    val editingTask: State<NoteTaskItem?> get() = _editingTask


    fun dispatch(event: TaskAddEvent) {
        when (event) {
            is TaskAddEvent.CreateTask -> createTask(event.userId, event.title)
            is TaskAddEvent.OnClickBack -> navigateTo(Screens.NotesTaskScreen.createRoute(event.userId))
        }
    }

    private fun createTask(userId: Int, title: String) {
        viewModelScope.launch {
            val note = _editingTask.value?.copy(title = title)
                ?: NoteTaskItem(
                    id = 0,
                    type = TypeItem.TASK,
                    title = title,
                    userId = userId,
                    isComplete = false,
                    createTime = 0L
                )
            addTaskUseCase(note)
            navigateTo(Screens.NotesTaskScreen.createRoute(userId))
        }
    }

    private fun navigateTo(route: String) {
        viewModelScope.launch {
            _effect.emit(TaskAddSideEffect.NavigateTo(route))
        }
    }
}

