package com.example.healthmind;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class Notification extends Application {
    public static String CHANNEL_ID = "br.com.healthMind";
    public static String CHANNEL_NAME = "channel";

    public static String getChannelId(Context context){
        if(Build.VERSION.SDK_INT < 26){
            return CHANNEL_ID;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = notificationManager.getNotificationChannel(CHANNEL_ID);

        if(channel == null){
            channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        return CHANNEL_ID;
    }
}