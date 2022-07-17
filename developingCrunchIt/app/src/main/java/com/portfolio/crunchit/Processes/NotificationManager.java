package com.portfolio.crunchit.Processes;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.content.Intent;
import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.portfolio.crunchit.R;


public class NotificationManager extends IntentService {
    private static int NOTIFICATION_ID = 0;
    private static final String CHANNEL_ID = "MAIN CHANNEL";
    public FirebaseDatabase database;
    public DatabaseReference ordersRef;
    public DatabaseReference usersRef;

    public NotificationManager(String name) {
        super(name);
    }

    public NotificationManager() {
        super("Notification Manager");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference();
        ordersRef = database.getReference("Orders");

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                NotificationBuilder();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void NotificationBuilder(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence managerName = "CRUNCHIT NOIFICATION MANAGER";
            String description = "Order update";
            int priority = android.app.NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, managerName, priority);
            channel.setDescription(description);
            android.app.NotificationManager notificationManager = getSystemService(android.app.NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Order update")
                .setContentText("An order has been update. Check the app to know more.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        notificationManager.notify(NOTIFICATION_ID++, builder.build());


    }
}