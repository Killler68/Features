package com.example.features.features.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.features.model.FeaturesEvent
import com.example.features.features.model.FeaturesSideEffect
import com.example.features.features.model.FeaturesState
import com.example.features.navigation.Screens
import com.example.features.notes.common.usecase.GetNotesUseCase
import com.example.features.weather.viewmodel.PreviewBarWeatherUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeaturesViewModel(
    private val features: FeaturesUseCase,
    private val drawerItems: GetDrawerItems,
    private val previewBarWeatherUseCase: PreviewBarWeatherUseCase,
    private val notesUseCase: GetNotesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<FeaturesState>(FeaturesState.Loading)
    val state: StateFlow<FeaturesState> get() = _state.asStateFlow()

    private val _effect = MutableSharedFlow<FeaturesSideEffect>()
    val effect: SharedFlow<FeaturesSideEffect> get() = _effect.asSharedFlow()

    fun dispatch(event: FeaturesEvent) {
        when (event) {
            FeaturesEvent.NavigateToAbout -> destination(Screens.AboutScreen.route)
            is FeaturesEvent.NavigateToFeature -> destination(event.featureId)
            is FeaturesEvent.NavigateToProfile -> destination(
                Screens.UserAdditionalInfo.createRoute(
                    event.userId
                )
            )

            FeaturesEvent.NavigateToSettings -> destination(Screens.SettingsScreen.route)
            is FeaturesEvent.LoadAllData -> loadData(event.userId)
        }
    }

    private fun loadData(userId: Int) {
        viewModelScope.launch {
            try {
                val drawerItems = drawerItems()
                val weather = previewBarWeatherUseCase()
                val notes = notesUseCase(userId)
                val features = features()
                _state.value = FeaturesState.Success(
                    itemDrawer = drawerItems,
                    itemWeather = weather,
                    itemNote = notes,
                    itemFeature = features
                )
            } catch (e: Exception) {
                _state.value = FeaturesState.Error(e.localizedMessage ?: "Ошибка загрузки данных")
            }
        }
    }

    private fun destination(route: String) {
        viewModelScope.launch {
            _effect.emit(FeaturesSideEffect.NavigateTo(route))
        }
    }
}

