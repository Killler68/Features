package com.example.features.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.R
import com.example.features.common.navigation.Screens
import com.example.features.settings.presentation.models.SettingsEvent
import com.example.features.settings.presentation.models.SettingsSideEffect
import com.example.features.settings.presentation.models.SettingsState
import com.example.features.settings.domain.DeleteUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _effect = MutableSharedFlow<SettingsSideEffect>()
    val effect: SharedFlow<SettingsSideEffect> get() = _effect.asSharedFlow()

    private val _state = MutableStateFlow<SettingsState>(SettingsState.Loading)
    val state: StateFlow<SettingsState> get() = _state.asStateFlow()

    fun dispatch(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.DeleteUser -> deleteUser(event.userId)
           is SettingsEvent.OnBack -> navigateTo(Screens.Features.createRoute(event.userId))
        }
    }

    private fun deleteUser(userId: Int) =
        viewModelScope.launch {
            _state.value = SettingsState.Loading
            try {
                deleteUserUseCase(userId)

                _state.value = SettingsState.Success
                navigateTo(Screens.Registration.route)
            } catch (e: Exception) {
                _state.value = SettingsState.Error(R.string.error_load)
            }
        }

    private fun navigateTo(route: String) {
        viewModelScope.launch {
            _effect.emit(SettingsSideEffect.NavigateTo(route))
        }
    }
}