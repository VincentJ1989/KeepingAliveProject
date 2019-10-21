package com.vincentj.keepingaliveproject.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class ForegroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                val channel =
                    NotificationChannel("foreground", "foreground", NotificationManager.IMPORTANCE_LOW)
                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager ?: return
                manager.createNotificationChannel(channel)

                val notification = NotificationCompat.Builder(this, "foreground").setAutoCancel(true)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .setOngoing(true)
                    .setPriority(NotificationManager.IMPORTANCE_LOW)
                    .build()
                startForeground(1, notification)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 -> {
                // 如果 18 以上的设备 启动一个Service startForeground给相同的id
                // 然后结束那个Service
                startForeground(1, Notification())
                startService(Intent(this, InnerService::class.java))
            }
            else -> startForeground(1, Notification())
        }

    }

     class InnerService : Service() {
        override fun onBind(intent: Intent?): IBinder? {
            return null
        }

        override fun onCreate() {
            super.onCreate()
            startForeground(1, Notification())
            stopSelf()
        }

    }
}