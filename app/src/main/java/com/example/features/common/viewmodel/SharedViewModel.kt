package com.example.features.common.viewmodel

import androidx.lifecycle.ViewModel
import com.example.features.common.database.user.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> get() = _currentUser

    fun setCurrentUser(user: User) {
        _currentUser.value = user
    }
}