package com.vincentj.keepingaliveproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vincentj.keepingaliveproject.account.AccountHelper
import com.vincentj.keepingaliveproject.onePiexl.KeepManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 1像素保活
//        KeepManager.registerKeep(this)

        // 前台服务保活
//        startService(Intent(this, ForegroundService::class.java))

        // StickyService拉活
//        startService(Intent(this, StickyService::class.java))

        // 账户同步拉活
        AccountHelper.addAccount(this)
        AccountHelper.autoSync()

        // JobScheduler拉活
//        MyJobService.startJob(this)

        // 双进程+JobScheduler拉活
//        com.vincentj.keepingaliveproject.daemon.MyJobService.startJob(this)
    }

    override fun onDestroy() {
        KeepManager.unregisterKeep(this)
        super.onDestroy()
    }
}
