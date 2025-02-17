package com.example.features.registration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.registration.model.RegistrationEvent
import com.example.features.registration.model.RegistrationState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val sharedViewModel: SharedViewModel
) : ViewModel() {

    private val _state = MutableStateFlow<RegistrationState>(RegistrationState.Loading)
    val state: StateFlow<RegistrationState> get() = _state.asStateFlow()

    private val _event = MutableSharedFlow<RegistrationEvent>()
    val event: SharedFlow<RegistrationEvent> get() = _event.asSharedFlow()

    fun dispatch(
        event: RegistrationEvent,
        login: String = "",
        password: String = "",
        onUserCreated: (Int) -> Unit = {}
    ) {
        when (event) {
            RegistrationEvent.CreateUser -> createUser(login, password, onUserCreated)
            RegistrationEvent.NavigateToAuthorization -> navigateToAuthorization()
        }
    }

    private fun createUser(login: String, password: String, onUserCreated: (Int) -> Unit) {
        viewModelScope.launch {
            _state.value = RegistrationState.Loading
            try {
                val user = createUserUseCase(login, password)
                sharedViewModel.setCurrentUser(user)
                onUserCreated(user.id)
                _state.value = RegistrationState.Success
                _event.emit(RegistrationEvent.CreateUser)
            } catch (e: Exception) {
                _state.value = RegistrationState.Error(e.localizedMessage ?: "Error")
            }
        }
    }

    private fun navigateToAuthorization() {
        viewModelScope.launch {
            _event.emit(RegistrationEvent.NavigateToAuthorization)
        }
    }
}