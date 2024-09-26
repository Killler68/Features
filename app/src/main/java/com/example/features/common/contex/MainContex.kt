package com.example.features.common.contex

import android.content.Context
import com.example.features.common.application.AppComponent
import com.example.features.common.application.MainApp

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }