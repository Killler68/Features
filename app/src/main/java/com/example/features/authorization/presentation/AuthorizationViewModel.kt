package com.example.features.authorization.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.R
import com.example.features.authorization.domain.GetUserByLoginAndPasswordUseCase
import com.example.features.authorization.presentation.models.AuthorizationEvent
import com.example.features.authorization.presentation.models.AuthorizationSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthorizationViewModel(
    private val getUserUseCase: GetUserByLoginAndPasswordUseCase
) : ViewModel() {

    private val _effect = MutableSharedFlow<AuthorizationSideEffect>()
    val effect: SharedFlow<AuthorizationSideEffect> get() = _effect.asSharedFlow()

    fun dispatch(event: AuthorizationEvent) {
        when (event) {
            is AuthorizationEvent.User -> loadUser(event.login, event.password)
            AuthorizationEvent.NavigateToRegistration -> {
                //naming and can be inlined
                viewModelScope.launch {
                    _effect.emit(AuthorizationSideEffect.ToRegistration)
                }
            }
        }
    }

    private fun loadUser(login: String, password: String) {
        viewModelScope.launch {  // launchSafely
            try {
                val userId = getUserUseCase(login, password)?.id
                if (userId != null) {
                    //naming and can be inlined
                    viewModelScope.launch {
                        _effect.emit(AuthorizationSideEffect.ToFeatures(userId))
                    }
                } else {
                    _effect.emit(AuthorizationSideEffect.ErrorMessage(R.string.error_load))
                }
            } catch (e: Exception) {
                _effect.emit(AuthorizationSideEffect.ErrorMessage(R.string.error_login_and_password))
            }
        }
    }
}