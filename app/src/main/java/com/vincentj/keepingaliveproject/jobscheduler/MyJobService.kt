package com.vincentj.keepingaliveproject.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.util.Log

class MyJobService : JobService() {
    override fun onStartJob(params: JobParameters): Boolean {
        Log.e(TAG, "onStartJob")
        //如果7.0以上要轮询
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            startJob(this)
        }

        return false
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return false
    }

    companion object {
        private val TAG = MyJobService::class.java.simpleName

        fun startJob(context: Context) {
            val jobScheduler =
                context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            // setPersisted 在设备重启依然执行
            // 需要增加权限 RECEIVE_BOOT_COMPLETED
            val builder = JobInfo.Builder(
                8,
                ComponentName(context.packageName, MyJobService::class.java.name)
            )
                .setPersisted(true)
            // 小于7.0
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                // 每个1s执行一次job
                //版本23开始改进，最小周期为5秒
                builder.setPeriodic(1000L)
            } else {
                //延迟执行任务
                builder.setMinimumLatency(1000L)
            }
            jobScheduler.schedule(builder.build())
        }
    }

}
