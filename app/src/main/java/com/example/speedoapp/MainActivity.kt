package com.example.speedoapp

import TokenManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import com.example.speedoapp.navigation.AppNavHost
import com.example.speedoapp.ui.theme.SpeedoAppTheme
import com.delasign.samplestarterproject.utils.readJsonFromAssets

import androidx.navigation.compose.NavHost
import com.example.speedoapp.api.InactivityManager

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedoAppTheme {
            }
        }
    }

    override fun onResume() {
        super.onResume()
        InactivityManager.userInteraction()
    }
}