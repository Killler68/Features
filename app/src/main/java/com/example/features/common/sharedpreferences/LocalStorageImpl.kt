package com.example.features.common.sharedpreferences

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


const val FIRST_LAUNCH_APP_KEY = "FIRST_LAUNCH_APP_KEY"

class LocalStorageImpl(
    private val context: Context
) : LocalStorage {

    override suspend fun setFirstLaunch() {
        withContext(Dispatchers.IO) {
            val sharedPreferences =
                context.getSharedPreferences(FIRST_LAUNCH_APP_KEY, Context.MODE_PRIVATE)
            sharedPreferences.edit()
                .putBoolean(FIRST_LAUNCH_APP_KEY, false)
                .apply()
        }
    }

    override suspend fun isFirstLaunch(): Boolean = withContext(Dispatchers.IO) {
        val sharedPreferences =
            context.getSharedPreferences(FIRST_LAUNCH_APP_KEY, Context.MODE_PRIVATE)
        sharedPreferences.getBoolean(FIRST_LAUNCH_APP_KEY, true)
    }
}
