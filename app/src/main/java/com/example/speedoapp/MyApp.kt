package com.example.speedoapp

import PreferencesManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.speedoapp.api.InactivityManager

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesManager.init(this)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel =
                NotificationChannel(
                    NotificationService.TRANSFER_NOTIFICATION_ID,
                    "Transfer",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            channel.description = "Used to show the status of money transfer result"

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}