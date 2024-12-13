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

    private var isManualUserSet = false

    fun getUser(login: String, password: String, callback: (Boolean, Int) -> Unit) {
        viewModelScope.launch {
            try {
                val user = getUserByLoginAndPassword(login, password)
                if (user != null) {
                    _currentUser.value = user
                    _error.value = null
                    callback(true, user.id)
                } else {
                    _error.value = "Пользователь не найден"
                    callback(false, 0)
                }
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.message}"
                callback(false, 0)
            }
        }
    }

    fun setCurrentUser(user: User?) {
        isManualUserSet = true
        _currentUser.value = user
    }

    fun clearCurrentUser() {
        isManualUserSet = false
        _currentUser.value = null
    }

    fun clearError() {
        _error.value = null
    }

    fun isUserManuallySet(): Boolean {
        return isManualUserSet
    }
}