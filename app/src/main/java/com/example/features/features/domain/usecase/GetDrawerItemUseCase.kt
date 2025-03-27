package com.example.features.features.domain.usecase

import com.example.features.features.domain.entities.DrawerItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDrawerItemUseCase(private val repository: FeaturesRepository) {

    suspend operator fun invoke(userId: Int): List<DrawerItem> = withContext(Dispatchers.IO) {
        repository.getDrawerItems(userId)
    }
}