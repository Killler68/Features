package com.example.features.common.application

import android.app.Application
import com.example.features.authorization.AuthorizationModule
import com.example.features.common.activity.ActivityModule
import com.example.features.common.module.DatabaseModule
import com.example.features.common.module.RepositoryModule
import com.example.features.common.sharedpreferences.LocalStorageModule
import com.example.features.features.FeaturesModule
import com.example.features.notes.noteslist.NotesModule
import com.example.features.notes.task.TaskModule
import com.example.features.profile.ProfileModule
import com.example.features.registration.RegistrationModule
import com.example.features.settings.SettingsModule
import com.example.features.weather.WeatherModule
import com.example.features.weather.detailed.WeatherDetailedModule
import com.example.features.welcome.WelcomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {

    private val modules by lazy {
        listOf(
            WelcomeModule.module,
            WeatherModule.modules,
            WeatherDetailedModule.module,
            SettingsModule.module,
            RegistrationModule.module,
            ProfileModule.module,
            TaskModule.module,
            NotesModule.module,
            FeaturesModule.module,
            AuthorizationModule.module,
            SharedModule.module,
            DatabaseModule.module,
            RepositoryModule.module,
            LocalStorageModule.module,
            ActivityModule.module
        )
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(modules)
        }
    }
}