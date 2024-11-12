package com.example.features.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.authorization.GetUserByLoginAndPassword
import com.example.features.common.database.user.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel(
    private val getUserByLoginAndPassword: GetUserByLoginAndPassword
) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> get() = _currentUser

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun getUser(login: String, password: String) {
        viewModelScope.launch {
            try {
                val user = getUserByLoginAndPassword(login, password)
                if (user != null) {
                    setCurrentUser(user)
                    _error.value = null
                } else {
                    _error.value = "Пользователь не найден"
                }
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.message}"
            }
        }
    }

    fun setCurrentUser(user: User?) {
        _currentUser.value = user
    }

    fun clearError() {
        _error.value = null
    }
}