package com.example.features.features.data

import com.example.features.R
import com.example.features.common.navigation.Screens
import com.example.features.common.repository.UserRepository
import com.example.features.features.domain.entities.DrawerItem
import com.example.features.features.domain.entities.Features
import com.example.features.features.domain.entities.FeaturesItemDrawer
import com.example.features.features.domain.usecase.FeaturesRepository

class FeaturesRepositoryImpl(
    private val userRepository: UserRepository
) : FeaturesRepository {

    override fun getFeatures(userId: Int): List<Features> =
        listOf(
            Features(
                "",
                "",
                "",
                0.0f,
                Screens.NotesTaskScreen.createRoute(userId)
            ),
            Features(
                "",
                "",
                "",
                0.0f,
                Screens.Weather.route
            )
        )

    override suspend fun getDrawerItems(userId: Int): List<DrawerItem> {
        return listOf(
            DrawerItem(
                id = FeaturesItemDrawer.PROFILE_PREVIEW,
                title = R.string.profile,
                image = R.drawable.profile
            ),
            DrawerItem(
                id = FeaturesItemDrawer.PROFILE,
                userLogin = userRepository.getUserById(userId)?.login,
                title = DEFAULT,
                image = DEFAULT
            ),
            DrawerItem(
                id = FeaturesItemDrawer.SETTINGS,
                title = R.string.settings,
                image = R.drawable.settings
            ),
            DrawerItem(
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



