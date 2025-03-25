package com.example.features.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.R
import com.example.features.common.navigation.Screens
import com.example.features.settings.domain.DeleteUserUseCase
import com.example.features.settings.presentation.models.SettingsEvent
import com.example.features.settings.presentation.models.SettingsSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _effect = MutableSharedFlow<SettingsSideEffect>()
    val effect: SharedFlow<SettingsSideEffect> get() = _effect.asSharedFlow()

    fun dispatch(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.DeleteUser -> deleteUser(event.userId)
            is SettingsEvent.OnBack -> navigateTo(Screens.Features.createRoute(event.userId))
        }
    }

    private fun deleteUser(userId: Int) =
        viewModelScope.launch {
            try {
                deleteUserUseCase(userId)
                navigateTo(Screens.Registration.route)
            } catch (e: Exception) {
                _effect.emit(SettingsSideEffect.ErrorMessage(R.string.error_load))
            }
        }

    private fun navigateTo(route: String) {
        viewModelScope.launch {
            _effect.emit(SettingsSideEffect.NavigateTo(route))
        }
    }
}