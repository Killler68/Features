package com.example.features.features.data

import com.example.features.R
import com.example.features.common.navigation.Screens
import com.example.features.common.repository.UserRepository
import com.example.features.features.domain.entities.DrawerItems
import com.example.features.features.domain.entities.Features
import com.example.features.features.domain.entities.FeaturesItemDrawer
import com.example.features.features.domain.usecase.FeaturesRepository

class FeaturesRepositoryImpl(
    private val userRepository: UserRepository
) : FeaturesRepository {

    override fun getFeatures(): List<Features> = features
    override suspend fun getDrawerItems(userId: Int): List<DrawerItems> {
        return listOf(
            DrawerItems(
                id = FeaturesItemDrawer.PROFILE_PREVIEW,
                title = R.string.profile,
                image = R.drawable.profile
            ),
            DrawerItems(
                id = FeaturesItemDrawer.PROFILE,
                userLogin = userRepository.getUserById(userId)?.login,
                title = DEFAULT,
                image = DEFAULT
            ),
            DrawerItems(
                id = FeaturesItemDrawer.SETTINGS,
                title = R.string.settings,
                image = R.drawable.settings
            ),
            DrawerItems(
                id = FeaturesItemDrawer.ABOUT,
                title = R.string.about_project,
                image = R.drawable.question
            ),
        )
    }

    companion object {
        const val DEFAULT = 0
    }
}

private val features = listOf(
    Features(
        "",
        "",
        "",
        0.0f,
        Screens.NotesList.route
    ),
    Features(
        "",
        "",
        "",
        0.0f,
        Screens.Weather.route
    )
)

