package com.example.features.features.usecase

import com.example.features.features.model.DrawerItems
import com.example.features.features.model.Features

interface FeaturesRepository {

    fun getFeatures(): List<Features>
    fun getDrawerItems(): List<DrawerItems>
}