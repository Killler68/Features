package com.example.features.common.sharedpreferences

interface LocalStorage { // no need for this interface

    fun setFirstLaunch()
    fun isFirstLaunch(): Boolean
}