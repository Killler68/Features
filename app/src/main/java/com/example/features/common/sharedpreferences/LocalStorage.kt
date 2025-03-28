package com.example.features.common.sharedpreferences

interface LocalStorage {

    fun setFirstLaunch()
    fun isFirstLaunch(): Boolean
}