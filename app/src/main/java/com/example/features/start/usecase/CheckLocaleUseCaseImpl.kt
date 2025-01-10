package com.example.features.start.usecase

import androidx.navigation.NavController
import com.example.features.common.sharedpreferences.LocalStorage
import com.example.features.navigation.Screens
import com.example.features.start.viewmodel.CheckLocaleUseCase

class CheckLocaleUseCaseImpl(
    private val localStorage: LocalStorage,
    private val navController: NavController
) : CheckLocaleUseCase {

   override suspend operator fun invoke() =
        if (localStorage.isFirstLaunch()) {
            navController.navigate(Screens.Welcome.route)
            localStorage.setFirstLaunch()
        } else navController.navigate(Screens.Authorization.route)
}

