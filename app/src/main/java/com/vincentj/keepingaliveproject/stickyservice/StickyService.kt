package com.vincentj.keepingaliveproject.stickyservice

import android.app.Service
import android.content.Intent
import android.os.IBinder

class StickyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

}
