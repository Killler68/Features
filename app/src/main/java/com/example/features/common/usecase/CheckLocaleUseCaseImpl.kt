package com.example.features.common.usecase

import com.example.features.common.constants.StartDestination
import com.example.features.common.sharedpreferences.LocalStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CheckLocaleUseCaseImpl(
    private val localStorage: LocalStorage,
) : CheckLocaleUseCase {

    override suspend operator fun invoke(): String = withContext(Dispatchers.IO) {
        return@withContext if (localStorage.isFirstLaunch()) {
            localStorage.setFirstLaunch()
            StartDestination.WELCOME.route
        } else {
            StartDestination.AUTHORIZATION.route
        }
    }
}


