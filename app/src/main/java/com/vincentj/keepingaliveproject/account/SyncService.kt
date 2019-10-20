package com.vincentj.keepingaliveproject.account

import android.accounts.Account
import android.app.Service
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log

class SyncService : Service() {
    private var mSyncAdapter: SyncAdapter? = null
    override fun onBind(intent: Intent): IBinder? {
        return mSyncAdapter!!.syncAdapterBinder
    }

    override fun onCreate() {
        super.onCreate()
        mSyncAdapter = SyncAdapter(application, true)
    }

    class SyncAdapter(context: Context, autoInitialize: Boolean) :
        AbstractThreadedSyncAdapter(context, autoInitialize) {

        override fun onPerformSync(
            account: Account,
            extras: Bundle,
            authority: String,
            provider: ContentProviderClient,
            syncResult: SyncResult
        ) {
            Log.e(TAG, "同步账户")
            // 与互联网或者本地数据库同步
        }
    }

    companion object {
        private val TAG = SyncService::class.java.simpleName
    }
}
