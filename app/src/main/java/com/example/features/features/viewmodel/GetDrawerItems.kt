package com.example.features.features.viewmodel

import com.example.features.features.model.DrawerItems

interface GetDrawerItems {

    operator fun invoke(): List<DrawerItems>
}