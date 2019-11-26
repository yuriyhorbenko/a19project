package com.example.my19.ShopClasses;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my19.PagerActivity;
import com.example.my19.R;

public class NotificationActivity extends AppCompatActivity {
    private static final String NOTIFICATION_CHANNEL_ID = "my19.notification_channel_id";
    private static final String NOTIFICATION_CHANNEL_NAME = "my19";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        int small_icon = R.drawable.ic_launcher_foreground;
        Intent notificationIntent = new Intent(this, PagerActivity.class);
        NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

        Notification.Builder mBuilder =
                (Notification.Builder) new Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
                        .setSmallIcon(small_icon)
                        .setContentTitle("Новый пуш")
                        .setAutoCancel(true)
                        .setTicker("My 19")
                        .setOnlyAlertOnce(true)
                        .setColor(Color.RED)
                        .setWhen(System.currentTimeMillis())
                        .setChannelId(NOTIFICATION_CHANNEL_ID)
                        .setContentText("У вас новое сообщение");

        PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(channel);
        mNotificationManager.notify(1, mBuilder.build());
    }
}
