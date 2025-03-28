package com.example.features.welcome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.navigation.Screens
import com.example.features.welcome.domain.entities.PagerItem
import com.example.features.welcome.domain.usecase.GetPagerItemUseCase
import com.example.features.welcome.presentation.models.WelcomeEvent
import com.example.features.welcome.presentation.models.WelcomeSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val getPagerItemUseCase: GetPagerItemUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<List<PagerItem>>(emptyList())
    val state: StateFlow<List<PagerItem>> get() = _state.asStateFlow()

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

    private fun loadPagerItems() {
        _state.value = getPagerItemUseCase()
    }

    private fun navigateTo(route: String) {
        viewModelScope.launch {
            _effect.emit(WelcomeSideEffect.NavigateTo(route))
        }
    }
}