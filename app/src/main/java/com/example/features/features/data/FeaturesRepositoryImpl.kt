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
                title = "Профиль",
                image = R.drawable.profile
            ),
            DrawerItems(
                id = FeaturesItemDrawer.PROFILE,
                userLogin = userRepository.getUserById(userId)?.login,
                title = "Настройки",
                image = 0
            ),
            DrawerItems(
                id = FeaturesItemDrawer.SETTINGS,
                title = "Настройки",
                image = R.drawable.settings
            ),
            DrawerItems(
                id = FeaturesItemDrawer.ABOUT,
                title = "О приложении",
                image = R.drawable.question
            ),
        )
    }
}

private val features = listOf(
    Features(
        "Перейдите и посмотрите что в задачах",
        "Создайте заметку или задачу",
        "",
        0.0f,
        Screens.NotesList.route
    ),
    Features(
        "Погода сегодня прекрасна не так ли?",
        "Выберите город для отображения погоды",
        "",
        0.0f,
        Screens.Weather.route
    )
)

