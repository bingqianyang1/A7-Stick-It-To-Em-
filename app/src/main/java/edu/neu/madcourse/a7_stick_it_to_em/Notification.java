package edu.neu.madcourse.a7_stick_it_to_em;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification extends Application {
    public static final String CHANNEL_ID = "channel";
    private static NotificationManagerCompat notificationManagerCompat;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotification();
        notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    private void createNotification() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Message Notification Channel");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }

    public static void sendNotification(Context context, String sticker) {
        android.app.Notification notification = new NotificationCompat.Builder(context, Notification.CHANNEL_ID)
                .setSmallIcon(getSticker(sticker))
                .setContentTitle("You got a new STICKER.")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    private static int getSticker(String sticker) {
        switch (sticker) {
            case "Sticker1":
                return R.drawable.sticker1;
            case "Sticker2":
                return R.drawable.sticker2;
            case "Sticker3":
                return R.drawable.sticker3;
            case "Sticker4":
                return R.drawable.sticker4;
        }

        return R.drawable.sticker1;
    }
}
