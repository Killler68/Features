package com.example.features.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.navigateActivityToFragment(containerId: Int, fragment: Fragment) {
    this.supportFragmentManager.beginTransaction().replace(containerId, fragment)
        .addToBackStack(fragment.javaClass.simpleName).commit()
}