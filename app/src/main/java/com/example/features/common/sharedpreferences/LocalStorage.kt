package com.example.features.common.sharedpreferences

interface LocalStorage {


    suspend fun setFirstLaunch()
    suspend fun isFirstLaunch(): Boolean
}