package com.example.features.common.application

import android.app.Application
import com.example.features.authorization.di.AuthorizationModule
import com.example.features.common.activity.di.ActivityModule
import com.example.features.common.module.DatabaseModule
import com.example.features.common.module.RepositoryModule
import com.example.features.common.sharedpreferences.LocalStorageModule
import com.example.features.features.di.FeaturesModule
import com.example.features.notes.di.NoteAddModule
import com.example.features.notes.di.NoteDetailedModule
import com.example.features.notes.di.NotesTaskModule
import com.example.features.notes.di.TaskAddModule
import com.example.features.profile.di.ProfileModule
import com.example.features.registration.di.RegistrationModule
import com.example.features.settings.di.SettingsModule
import com.example.features.weather.di.WeatherModule
import com.example.features.weatherdetailed.di.WeatherDetailedModule
import com.example.features.welcome.di.WelcomeModule
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
            TaskAddModule.module,
            NoteAddModule.module,
            NoteDetailedModule.module,
            NotesTaskModule.module,
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