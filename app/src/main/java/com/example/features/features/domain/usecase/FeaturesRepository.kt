package com.example.features.features.domain.usecase

import com.example.features.features.domain.entities.DrawerItems
import com.example.features.features.domain.entities.Features

interface FeaturesRepository {

    fun getFeatures(): List<Features>
    fun getDrawerItems(): List<DrawerItems>
}