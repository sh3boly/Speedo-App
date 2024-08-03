package com.example.speedoapp

import TokenManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.speedoapp.navigation.AppNavHost
import com.example.speedoapp.ui.theme.SpeedoAppTheme
import androidx.navigation.compose.NavHost

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedoAppTheme {
                AppNavHost(firstTime = TokenManager.isFirstTimeLaunch())
            }
        }
    }
}