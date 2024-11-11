package com.example.features.registration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val sharedViewModel: SharedViewModel
) : ViewModel() {

    fun createUser(login: String, password: String) {
        viewModelScope.launch {
            val user = createUserUseCase(login, password)
            sharedViewModel.setCurrentUser(user)
        }
    }
}