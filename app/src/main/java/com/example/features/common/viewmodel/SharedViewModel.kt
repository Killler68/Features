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

    fun setCurrentUser(user: User?) {
        _currentUser.value = user
    }

    fun getUser(login: String, password: String) {
        viewModelScope.launch {
            val user = getUserByLoginAndPassword(login, password)
            setCurrentUser(user)
        }
    }
}