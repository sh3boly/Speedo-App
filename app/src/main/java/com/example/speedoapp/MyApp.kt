package com.example.speedoapp

import PreferencesManager
import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferencesManager.init(this)
    }


}