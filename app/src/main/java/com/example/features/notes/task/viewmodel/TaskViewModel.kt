package com.example.features.notes.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.notes.task.model.TaskModel
import com.example.features.notes.task.usecase.CreateTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(
    private val sharedViewModel: SharedViewModel,
    private val addTaskUseCase: CreateTaskUseCase
) : ViewModel() {


    fun createTask(task: TaskModel) {
        viewModelScope.launch {
            val currentUser = sharedViewModel.currentUser.value
            currentUser?.let { user ->
                addTaskUseCase(task.copy(userId = user.id))
            }
        }
    }
}
