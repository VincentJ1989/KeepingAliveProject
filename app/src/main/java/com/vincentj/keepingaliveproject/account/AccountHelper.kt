package com.vincentj.keepingaliveproject.account

import android.accounts.Account
import android.accounts.AccountManager
import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.util.Log

import androidx.annotation.RequiresPermission


object AccountHelper {
    private val TAG = AccountHelper::class.java.simpleName
    private const val ACCOUNT_TYPE = "com.98du.step.account"
    fun addAccount(context: Context) {
        val accountManager = context.getSystemService(Context.ACCOUNT_SERVICE) as AccountManager
        // 获得此类型的账户
        // 需要权限
        val accounts = accountManager.getAccountsByType(ACCOUNT_TYPE)
        if (accounts.isNotEmpty()) {
            Log.e(TAG, "账户已存在")
            return
        }
        val account = Account("step", ACCOUNT_TYPE)
        // 给这个账户类型添加一个账户
        // 需要权限
        accountManager.addAccountExplicitly(account, "xx", Bundle())
    }

    /**
     * 设置账户自动同步
     */
    @RequiresPermission("android.permission.WRITE_SYNC_SETTINGS")
    fun autoSync() {
        val account = Account("step", ACCOUNT_TYPE)
        // 下面三个都需要一个权限 WRITE_SYNC_SETTING
        // 设置同步
        ContentResolver.setIsSyncable(account, "com.98du.step.provider", 1)
        // 自动同步
        ContentResolver.setSyncAutomatically(account, "com.98du.step.provider", true)
        // 设置同步周期
        ContentResolver.addPeriodicSync(account, "com.98du.step.provider", Bundle(), 1)
    }
}
