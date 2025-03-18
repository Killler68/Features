package com.example.features.welcome.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.navigation.Screens
import com.example.features.welcome.models.WelcomeEvent
import com.example.features.welcome.models.WelcomeSideEffect
import com.example.features.welcome.models.WelcomeState
import com.example.features.welcome.usecase.WelcomeUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val welcomeUseCase: WelcomeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<WelcomeState>(WelcomeState.Loading)
    val state: StateFlow<WelcomeState> get() = _state.asStateFlow()

    private val _effect = MutableSharedFlow<WelcomeSideEffect>()
    val effect: SharedFlow<WelcomeSideEffect> get() = _effect.asSharedFlow()

    init {
        dispatch(WelcomeEvent.LoadPagerItem)
    }

    fun dispatch(event: WelcomeEvent) {
        when (event) {
            WelcomeEvent.LoadPagerItem -> loadPagerItems()
            WelcomeEvent.ToAuthorization -> navigateTo(Screens.Authorization.route)
            WelcomeEvent.ToRegistration -> navigateTo(Screens.Registration.route)
        }
    }

    private fun loadPagerItems() = viewModelScope.launch {
        try {
            val itemPager = welcomeUseCase()
            _state.value = WelcomeState.Success(itemPager)
        } catch (e: Exception) {
            _state.value = WelcomeState.Error(e.localizedMessage ?: "Error load")
        }
    }

    private fun navigateTo(route: String) {
        viewModelScope.launch {
            _effect.emit(WelcomeSideEffect.NavigateTo(route))
        }
    }
}