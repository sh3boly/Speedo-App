package com.example.speedoapp

import TokenManager
import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        TokenManager.init(this) // Initialize SharedPreferences
    }
}