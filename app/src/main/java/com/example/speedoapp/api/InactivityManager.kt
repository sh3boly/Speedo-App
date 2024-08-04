package com.example.speedoapp.api

import TokenManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedoapp.navigation.AppRoutes

object InactivityManager {
    private lateinit var navController: NavController
    fun init(controller: NavController) {
        navController = controller

    }

    private val onInactivity: () -> Unit = {
        if (TokenManager.getToken() != null) {
            TokenManager.removeToken()
            navController.navigate(AppRoutes.SIGNIN_ROUTE)
            Toast.makeText(navController.context, "Session expired", Toast.LENGTH_LONG).show()
            userInteraction()
        }else{
            userInteraction()
        }
    }

    private val handler = Handler(Looper.getMainLooper())
    private val inactivityRunnable = Runnable { onInactivity() }
    private var inactivityTimeoutMillis: Long = 3600000 / 2 // 30 minutes in milliseconds



    fun setInactivityTimeout(timeoutMillis: Long) {
        inactivityTimeoutMillis = timeoutMillis
    }

    fun userInteraction() {
        handler.postDelayed(inactivityRunnable, inactivityTimeoutMillis)
    }

    fun stop() {
        handler.removeCallbacks(inactivityRunnable)
    }

}