package com.ggr3ml1n.moretech50

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            "location",
            "Location",
            NotificationManager.IMPORTANCE_LOW
        )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}