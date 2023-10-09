package com.example.anmp_week4.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.android.volley.Request.Priority
import com.example.anmp_week4.R

fun createNotificationChannel(context: Context, priority:Int, showBadge:
Boolean, description: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = context.getString(R.string.app_name)
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, priority)
        channel.description = description
        channel.setShowBadge(showBadge)

        val manager =
            context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}
