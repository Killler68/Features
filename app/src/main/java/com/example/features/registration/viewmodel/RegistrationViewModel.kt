package com.example.features.registration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.database.user.model.User
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.registration.model.RegistrationEvent
import com.example.features.registration.model.RegistrationState
import com.example.features.registration.usecase.GetUserByLoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserByLoginUseCase: GetUserByLoginUseCase,
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
    ) {
        when (event) {
            RegistrationEvent.CreateUser -> createUser(login, password)
            RegistrationEvent.NavigateToAuthorization -> navigateToAuthorization()
        }
    }

    private fun createUser(login: String, password: String) {
        viewModelScope.launch {
            _state.value = RegistrationState.Loading
            try {
                val existingUser = getUserByLoginUseCase(login)
                if (existingUser != null) {
                    _state.value =
                        RegistrationState.Error("Пользователь с таким логином уже существует")
                    return@launch
                }

                val userId = createUserUseCase(login, password)
                sharedViewModel.setCurrentUser(User(userId, login, password))

                _state.value = RegistrationState.Success
                _event.emit(RegistrationEvent.CreateUser)
            } catch (e: Exception) {
                _state.value = RegistrationState.Error(e.localizedMessage ?: "Ошибка регистрации")
            }
        }
    }

    private fun navigateToAuthorization() {
        viewModelScope.launch {
            _event.emit(RegistrationEvent.NavigateToAuthorization)
        }
    }
}