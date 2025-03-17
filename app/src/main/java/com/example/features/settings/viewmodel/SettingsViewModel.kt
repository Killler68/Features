package com.example.features.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.settings.model.SettingsEvent
import com.example.features.settings.model.SettingsState
import com.example.features.settings.usecase.DeleteUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val deleteUserUseCase: DeleteUserUseCase,
    private val sharedViewModel: SharedViewModel
) : ViewModel() {

    private val _event = MutableSharedFlow<SettingsEvent>()
    val event: SharedFlow<SettingsEvent> get() = _event.asSharedFlow()

    private val _state = MutableStateFlow<SettingsState>(SettingsState.Loading)
    val state: StateFlow<SettingsState> get() = _state.asStateFlow()

    fun dispatch(event: SettingsEvent) {
        when (event) {
            SettingsEvent.DeleteUser -> deleteUser()
            SettingsEvent.OnBack -> navigateBack()
        }
    }

    private fun deleteUser() =
        viewModelScope.launch {
            _state.value = SettingsState.Loading
            try {
                val userId = sharedViewModel.currentUser.value?.id
                userId?.let { deleteUserUseCase(it) }

                _state.value = SettingsState.Success
                _event.emit(SettingsEvent.DeleteUser)
            } catch (e: Exception) {
                _state.value = SettingsState.Error(e.localizedMessage ?: "Error")
            }
        }

    private fun navigateBack() {
        viewModelScope.launch {
            _event.emit(SettingsEvent.OnBack)
        }
    }
}