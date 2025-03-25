package com.example.features.features.domain.usecase

import com.example.features.features.domain.entities.DrawerItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDrawerItemsUseCase(private val repository: FeaturesRepository) {

    suspend operator fun invoke(userId: Int): List<DrawerItems> = withContext(Dispatchers.IO) {
        repository.getDrawerItems(userId)
    }
}