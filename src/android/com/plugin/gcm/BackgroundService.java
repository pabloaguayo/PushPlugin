package com.plugin.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class BackgroundService extends IntentService {
    private static final String TAG = "BackgroundService";

    public BackgroundService() {
        super("push-notification-background-service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v(TAG, "Intent Handled");
    }
}