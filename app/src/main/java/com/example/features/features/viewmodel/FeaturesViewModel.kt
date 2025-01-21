package com.example.features.features.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.common.viewmodel.SharedViewModel
import com.example.features.features.model.DrawerItems
import com.example.features.notes.common.model.NotesModel
import com.example.features.notes.common.usecase.GetNotesUseCase
import com.example.features.weather.model.PreviewBarWeather
import com.example.features.weather.viewmodel.PreviewBarWeatherUseCase
import kotlinx.coroutines.launch

class FeaturesViewModel(
    private val features: FeaturesUseCase,
    private val drawerItems: GetDrawerItems,
    private val previewBarWeatherUseCase: PreviewBarWeatherUseCase,
    private val notesUseCase: GetNotesUseCase
) : ViewModel() {

    private val _drawer = mutableStateOf<List<DrawerItems>>(emptyList())
    val drawer: State<List<DrawerItems>> get() = _drawer

    private val _weather = mutableStateOf(PreviewBarWeather("London", "", "", 0.0f, ""))
    val weather: State<PreviewBarWeather> get() = _weather

    private var _notes = mutableStateOf<List<NotesModel>>(emptyList())
    val notes: State<List<NotesModel>> get() = _notes

    fun loadFeatures() = features()

    fun getDrawerItems() {
        viewModelScope.launch {
            _drawer.value = drawerItems()

        }
    }

    fun loadWeather() {
        viewModelScope.launch {
            _weather.value = previewBarWeatherUseCase()
        }
    }

    fun loadLastNotes(userId: Int) {
        viewModelScope.launch {
                _notes.value = notesUseCase(userId)
            }
        }
    }

