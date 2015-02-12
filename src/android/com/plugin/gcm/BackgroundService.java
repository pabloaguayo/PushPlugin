package com.plugin.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import android.os.Bundle;

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

        // Get clicked button name (in the name we have the token)
        Bundle extras = intent.getExtras();
        Bundle originalExtras = extras.getBundle("pushBundle");
        String button = originalExtras.getString("action", "");
        Log.v(TAG, "Recovered extra: " + button);

        String token = "";
        if(button.contains("#"))
            token = button.split("#")[1];

        try {
            URL url = new URL("http://192.168.1.6:8080/nire/process/processCommand?token=" + token);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                int responseCode = urlConnection.getResponseCode();
                Log.v(TAG, "Response code: " + responseCode);
            }
            finally {
                urlConnection.disconnect();
            }
        }
        catch(MalformedURLException e) {
            Log.v(TAG, "Exception. Malformed URL");
        }
        catch(IOException e) {
            Log.v(TAG, "Exception. IOException");
        }

    }
}