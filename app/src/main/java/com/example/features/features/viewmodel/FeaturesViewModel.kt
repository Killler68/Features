package com.example.features.features.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.features.model.DrawerItems
import kotlinx.coroutines.launch

class FeaturesViewModel(
    private val features: FeaturesUseCase,
    private val drawerItems: GetDrawerItems
) : ViewModel() {

    private val _drawer = mutableStateOf<List<DrawerItems>>(emptyList())
    val drawer: State<List<DrawerItems>> get() = _drawer


    fun loadFeatures() = features()

    fun getDrawerItems() {
        viewModelScope.launch {
            _drawer.value = drawerItems()

        }
    }
}
