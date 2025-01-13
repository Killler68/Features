package com.example.features.common.sharedpreferences

import android.content.Context


const val FIRST_LAUNCH_APP_KEY = "FIRST_LAUNCH_APP_KEY"

class LocalStorageImpl(
    private val context: Context
) : LocalStorage {

    override fun setFirstLaunch() {
        val sharedPreferences =
            context.getSharedPreferences(FIRST_LAUNCH_APP_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putBoolean(FIRST_LAUNCH_APP_KEY, false)
            .apply()
    }

    override fun isFirstLaunch(): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(FIRST_LAUNCH_APP_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(FIRST_LAUNCH_APP_KEY, true)

    }
}
