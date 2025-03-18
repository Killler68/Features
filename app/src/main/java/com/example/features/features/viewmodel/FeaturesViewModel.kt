package com.example.features.features.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.strings.city
import com.example.features.features.model.FeaturesEvent
import com.example.features.features.model.FeaturesSideEffect
import com.example.features.features.model.FeaturesState
import com.example.features.navigation.Screens
import com.example.features.notes.common.usecase.GetNotesUseCase
import com.example.features.weather.usecase.WeatherPreviewBarUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeaturesViewModel(
    private val features: FeaturesUseCase,
    private val drawerItems: GetDrawerItems,
    private val weatherPreviewBarUseCase: WeatherPreviewBarUseCase,
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
                Screens.Profile.createRoute(
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
                _state.value = FeaturesState.Success(
                    itemDrawer = drawerItems,
                    itemWeather = null,
                    itemNote = emptyList(),
                    itemFeature = features(),
                    isNotesLoading = true,
                    isWeatherLoading = true
                )

                val notes = notesUseCase(userId)
                _state.update {
                    (it as? FeaturesState.Success)?.copy(
                        itemNote = notes,
                        isNotesLoading = false
                    ) ?: it
                }

                val weather = weatherPreviewBarUseCase(city)
                _state.update {
                    (it as? FeaturesState.Success)?.copy(
                        itemWeather = weather,
                        isWeatherLoading = false
                    ) ?: it
                }

            } catch (e: Exception) {
                _state.value = _state.value
            }
        }
    }

    private fun destination(route: String) {
        viewModelScope.launch {
            _effect.emit(FeaturesSideEffect.NavigateTo(route))
        }
    }
}

