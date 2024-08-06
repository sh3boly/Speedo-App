package com.example.speedoapp.api

import PreferencesManager
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedoapp.constants.Constants.INACTIVITY_TIME
import com.example.speedoapp.navigation.AppRoutes
import com.example.speedoapp.ui.homepage.HomeViewModel

object InactivityManager {
    private lateinit var navController: NavController
    private lateinit var viewModel: HomeViewModel
    fun init(controller: NavController) {
        navController = controller

    }
    fun initViewModel(homeViewModel: HomeViewModel){
        viewModel = homeViewModel
    }

    private val onInactivity: () -> Unit = {
        if (PreferencesManager.getToken() != null) {
            viewModel.logout()
            PreferencesManager.removeToken()
            navController.navigate(AppRoutes.SIGNIN_ROUTE)
            Toast.makeText(navController.context, "Session expired", Toast.LENGTH_LONG).show()
            userInteraction()
        }else{
            userInteraction()
        }
    }

    private val handler = Handler(Looper.getMainLooper())
    private val inactivityRunnable = Runnable { onInactivity() }
    private var inactivityTimeoutMillis: Long = INACTIVITY_TIME // 30 minutes in milliseconds



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