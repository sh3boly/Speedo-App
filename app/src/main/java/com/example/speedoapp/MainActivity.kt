package com.example.speedoapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import com.example.speedoapp.api.RetrofitFactory
import com.example.speedoapp.navigation.AppNavHost
import com.example.speedoapp.ui.homepage.HomeScreen
import com.example.speedoapp.ui.theme.SpeedoAppTheme
import androidx.navigation.compose.NavHost

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedoAppTheme {
                AppNavHost()
            }
        }
    }
}

