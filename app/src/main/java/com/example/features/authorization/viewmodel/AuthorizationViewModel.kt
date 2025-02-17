package com.example.features.authorization.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.authorization.model.AuthorizationEvent
import com.example.features.authorization.model.AuthorizationSideEffect
import com.example.features.authorization.model.AuthorizationState
import com.example.features.common.viewmodel.SharedViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthorizationViewModel(
    private val sharedViewModel: SharedViewModel
) : ViewModel() {

    private val _state = MutableStateFlow<AuthorizationState>(AuthorizationState.Loading)
    val state: StateFlow<AuthorizationState> get() = _state.asStateFlow()

    private val _effect = MutableSharedFlow<AuthorizationSideEffect>()
    val effect: SharedFlow<AuthorizationSideEffect> get() = _effect.asSharedFlow()

    fun dispatch(event: AuthorizationEvent) {
        when (event) {
            is AuthorizationEvent.User -> loadUser(event.login, event.password)
            AuthorizationEvent.NavigateToRegistration -> navigateTo(AuthorizationSideEffect.ToRegistration)
        }
    }

    private fun loadUser(login: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthorizationState.Loading
            try {
                sharedViewModel.getUser(login, password) { userFound, userId ->
                    viewModelScope.launch {
                        if (userFound) {
                            navigateTo(AuthorizationSideEffect.ToFeatures(userId))
                            _state.value = AuthorizationState.Success
                        } else {
                            _state.value = AuthorizationState.Error("Неверный логин или пароль")
                        }
                    }
                }
            } catch (e: Exception) {
                AuthorizationState.Error(e.localizedMessage ?: "Ошибка авторизации")
            }
        }
    }


    private fun navigateTo(destination: AuthorizationSideEffect) {
        viewModelScope.launch {
            _effect.emit(destination)
        }
    }
}