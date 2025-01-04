package com.example.features.welcome.usecase

import com.example.features.welcome.models.PagerItems

interface WelcomeRepository {

    fun getPagerItem(): List<PagerItems>
}