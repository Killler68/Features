package com.example.features.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.settings.usecase.DeleteUserUseCase
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val deleteUserUseCase: DeleteUserUseCase,
) : ViewModel() {

    fun deleteUser(userId: Int) =
        viewModelScope.launch {
            deleteUserUseCase(userId)
        }
}