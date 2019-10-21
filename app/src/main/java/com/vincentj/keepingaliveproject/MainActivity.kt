package com.vincentj.keepingaliveproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vincentj.keepingaliveproject.stickyservice.StickyService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 1像素保活
//        KeepManager.registerKeep(this)

        // 前台服务保活
//        startService(Intent(this, ForegroundService::class.java))
        // 无声音乐保活
//        startService(Intent(this,PlayerMusicService::class.java))

        // StickyService拉活
        startService(Intent(this, StickyService::class.java))

        // 账户同步拉活
//        AccountHelper.addAccount(this)
//        AccountHelper.autoSync()

//         JobScheduler拉活
//        MyDaemonJobService.startJob(this)

        // 双进程+JobScheduler拉活
//        MyDaemonJobService.startJob(this)
    }

    override fun onDestroy() {
//        KeepManager.unregisterKeep(this)
        super.onDestroy()
    }
}
