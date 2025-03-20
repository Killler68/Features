package com.example.features.features.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.navigation.Screens
import com.example.features.common.strings.city
import com.example.features.features.presentation.models.FeaturesEvent
import com.example.features.features.presentation.models.FeaturesSideEffect
import com.example.features.features.presentation.models.FeaturesState
import com.example.features.features.domain.usecase.FeaturesUseCase
import com.example.features.features.domain.usecase.GetDrawerItemsUseCase
import com.example.features.notes.common.usecase.GetNotesUseCase
import com.example.features.weather.domain.usecase.WeatherPreviewBarUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeaturesViewModel(
    private val userId: Int,
    private val features: FeaturesUseCase,
    private val drawerItems: GetDrawerItemsUseCase,
    private val weatherPreviewBarUseCase: WeatherPreviewBarUseCase,
    private val notesUseCase: GetNotesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<FeaturesState>(FeaturesState.Loading)
    val state: StateFlow<FeaturesState> get() = _state.asStateFlow()

    private val _effect = MutableSharedFlow<FeaturesSideEffect>()
    val effect: SharedFlow<FeaturesSideEffect> get() = _effect.asSharedFlow()

    init {
        loadData(userId)
    }

    fun dispatch(event: FeaturesEvent) {
        when (event) {
            FeaturesEvent.NavigateToAbout -> destination(Screens.AboutScreen.route)
            is FeaturesEvent.NavigateToSettings -> destination(Screens.SettingsScreen.createRouter(event.userId))
            is FeaturesEvent.NavigateToFeature -> destination(event.featureId)
            is FeaturesEvent.NavigateToProfile -> destination(Screens.Profile.createRoute(userId))
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
                _state.value = FeaturesState.Error("Ошибка загрузки данных")
            }
        }
    }

    private fun destination(route: String) {
        viewModelScope.launch {
            _effect.emit(FeaturesSideEffect.NavigateTo(route))
        }
    }
}
