package com.plugin.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class BackgroundService extends IntentService {
    private static final String TAG = "BackgroundService";

    public BackgroundService() {
        super("push-notification-background-service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v(TAG, "Intent Handled");

        final NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
    	notificationManager.cancelAll();
    }
}