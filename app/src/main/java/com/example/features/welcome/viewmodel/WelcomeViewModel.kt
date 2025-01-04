package com.example.features.welcome.viewmodel

import androidx.lifecycle.ViewModel

class WelcomeViewModel(
    private val welcomeUseCase: WelcomeUseCase
) : ViewModel() {


    fun getPagerItems() = welcomeUseCase()
}