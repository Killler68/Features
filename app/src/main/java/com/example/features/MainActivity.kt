package com.example.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.features.common.navigateActivityToFragment
import com.example.features.registration.RegistrationFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container_main)

        navigateActivityToFragment(R.id.container, RegistrationFragment())
    }
}
