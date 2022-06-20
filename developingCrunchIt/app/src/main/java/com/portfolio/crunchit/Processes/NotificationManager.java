package com.portfolio.crunchit.Processes;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import androidx.annotation.Nullable;


public class NotificationManager extends IntentService {

    public NotificationManager(String name) {
        super(name);
    }

    public NotificationManager(){
        super("Notification Manager");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}