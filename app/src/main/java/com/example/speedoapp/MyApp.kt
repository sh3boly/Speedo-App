package com.example.speedoapp

import TokenManager
import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.widget.Toast
import com.example.speedoapp.api.InactivityManager
import com.example.speedoapp.api.RetrofitFactory

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        TokenManager.init(this)
    }


}