package com.example.arun.masterwork.syncadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by arun on 28/8/16.
 */

public class SyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static SyncAdapter syncAdapter = null;

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            syncAdapter = new SyncAdapter(getApplicationContext(), true);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }
}
