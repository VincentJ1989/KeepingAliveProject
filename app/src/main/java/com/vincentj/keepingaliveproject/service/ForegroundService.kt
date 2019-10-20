package com.vincentj.keepingaliveproject.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder

class ForegroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            // 小于18(4.3)不会有通知的图表
            startForeground(1, Notification())
        } else {
            // 18到26(7.0)会显示，但可以去除--更高的目前无法去除
            startForeground(1, Notification())
            startService(Intent(this, InnerService::class.java))
        }

    }

    inner class InnerService : Service() {
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