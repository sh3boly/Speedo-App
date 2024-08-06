package com.example.speedoapp

import AuthInterceptor
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speedoapp.navigation.AppNavHost
import com.example.speedoapp.ui.theme.SpeedoAppTheme
import com.example.speedoapp.api.InactivityManager
import com.example.speedoapp.api.RetrofitFactory
import com.example.speedoapp.navigation.AppRoutes.ADDCARD_ROUTE
import com.example.speedoapp.navigation.AppRoutes.LOADING_ROUTE
import com.example.speedoapp.navigation.AppRoutes.MY_CARDS
import com.example.speedoapp.navigation.AppRoutes.NOTIFICATIONS
import com.example.speedoapp.navigation.AppRoutes.OTP_CONNECT_ROUTE
import com.example.speedoapp.navigation.AppRoutes.OTP_ROUTE
import com.example.speedoapp.ui.MyCards.MyCards
import com.example.speedoapp.ui.addcard.AddCardScreen
import com.example.speedoapp.ui.addcard.LoadingScreen
import com.example.speedoapp.ui.addcard.OTP
import com.example.speedoapp.ui.addcard.OTPconnected
import com.example.speedoapp.ui.notifications.TransactionListScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedoAppTheme {
                AppContent()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        InactivityManager.userInteraction()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppContent() {
    val context = LocalContext.current
    val isFirst = PreferencesManager.isFirstTimeLaunch()

    var hasNotificationPermission by remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasNotificationPermission = isGranted
    }

    LaunchedEffect(isFirst) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                hasNotificationPermission = true
            }
        } else {
            hasNotificationPermission = true
        }
    }

    AppNavHost(firstTime = isFirst)
}
