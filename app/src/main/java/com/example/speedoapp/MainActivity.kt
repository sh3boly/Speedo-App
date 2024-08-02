package com.example.speedoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import com.example.speedoapp.navigation.AppNavHost
import com.example.speedoapp.ui.theme.SpeedoAppTheme
import com.example.speedoapp.ui.tranfer.AmountScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedoAppTheme {
                Log.d("CurrencyAPI", "Response received: ")
                AppNavHost()
            }
        }
    }
}