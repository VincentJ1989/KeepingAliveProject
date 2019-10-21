package com.vincentj.keepingaliveproject.onePiexl

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

import java.lang.ref.WeakReference

object KeepManager {
    private var mKeepReceiver: KeepReceiver? = null
    private var mKeepActivity: WeakReference<Activity>? = null

    /**
     * 注册
     * @param context
     */
    fun registerKeep(context: Context) {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_SCREEN_ON)

        mKeepReceiver = KeepReceiver()
        context.registerReceiver(mKeepReceiver, filter)
    }

    /**
     * 开启1像素Activity
     * @param context
     */
    fun startKeep(context: Context) {
        val intent = Intent(context, KeepActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    /**
     * 反注册
     * @param context
     */
    fun unregisterKeep(context: Context) {
        if (mKeepReceiver != null) {
            context.unregisterReceiver(mKeepReceiver)
        }
    }

    /**
     * 关闭1像素Activity
     */
    fun finishKeep() {
        if (mKeepActivity != null) {
            val activity = mKeepActivity!!.get()
            activity?.finish()
            mKeepActivity = null
        }
    }

    /**
     * 设置引用
     * @param keep
     */
    fun setKeep(keep: Activity) {
        mKeepActivity = WeakReference(keep)
    }

}
