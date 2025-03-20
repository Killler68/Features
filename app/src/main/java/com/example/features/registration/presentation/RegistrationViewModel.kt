package com.example.features.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.authorization.domain.GetUserByLoginAndPasswordUseCase
import com.example.features.common.database.user.model.User
import com.example.features.common.navigation.Screens
import com.example.features.registration.presentation.models.RegistrationEvent
import com.example.features.registration.presentation.models.RegistrationSideEffect
import com.example.features.registration.presentation.models.RegistrationState
import com.example.features.registration.domain.CreateUserUseCase
import com.example.features.registration.domain.GetUserByLoginUseCase
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
    private val getUserByLoginAndPasswordUseCase: GetUserByLoginAndPasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<RegistrationState>(RegistrationState.Loading)
    val state: StateFlow<RegistrationState> get() = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RegistrationSideEffect>()
    val effect: SharedFlow<RegistrationSideEffect> get() = _effect.asSharedFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> get() = _currentUser.asStateFlow()

    fun dispatch(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.CreateUser -> createUser(event.login, event.password)
            RegistrationEvent.NavigateToAuthorization -> navigateTo(Screens.Authorization.route)
        }
    }

    private fun createUser(login: String, password: String) {
        viewModelScope.launch {
            _state.value = RegistrationState.Loading
            try {
                val existingUser = getUserByLoginUseCase(login)
                if (existingUser != null) {
                    _state.value = RegistrationState.Error("Пользователь с таким логином уже существует")
                    return@launch
                }

                createUserUseCase(login, password)

                val newUser = getUserByLoginAndPasswordUseCase(login, password)
                if (newUser == null) {
                    _state.value = RegistrationState.Error("Ошибка при создании пользователя")
                    return@launch
                }

                _currentUser.value = newUser
                navigateToFeatures(newUser.id)

                _state.value = RegistrationState.Success
            } catch (e: Exception) {
                _state.value = RegistrationState.Error(e.localizedMessage ?: "Ошибка регистрации")
            }
        }
    }

    private fun navigateTo(route: String) {
        viewModelScope.launch {
            _effect.emit(RegistrationSideEffect.NavigateTo(route))
        }
    }

    private fun navigateToFeatures(userId: Int) {
        viewModelScope.launch {
            _effect.emit(RegistrationSideEffect.NavigateTo(Screens.Features.createRoute(userId)))
        }
    }
}