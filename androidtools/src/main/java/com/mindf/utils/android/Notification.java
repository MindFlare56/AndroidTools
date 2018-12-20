package com.mindf.utils.android;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class Notification {

    private Activity activity;
    private String channelId = "default";
    private int icon = R.drawable.ic_notifications;
    private String channelText = "channel text";
    private String description = "description";
    private String title = "title";
    private String contentText = "content text";
    private Class targetClass = Notification.class;
    private int id = 0;

    public Notification(Activity activity) {
        this.activity = activity;
    }

    public Notification setChannelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public Notification setIcon(int icon) {
        this.icon = icon;
        return this;
    }

    public Notification setChannelText(String channelText) {
        this.channelText = channelText;
        return this;
    }

    public Notification setDescription(String description) {
        this.description = description;
        return this;
    }

    public Notification setTitle(String title) {
        this.title = title;
        return this;
    }

    public Notification setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    public Notification setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
        return this;
    }

    public Notification setId(int id) {
        this.id = id;
        return this;
    }

    public Notification show() {
        //todo put this where it goes and handle moment they are sent
        //todo check to put a green tint to the icon
        NotificationCompat.Builder notificationBuilder = createNotificationBuilder(channelId, icon);
        createNotificationIntent();
        createNotificationChannel(channelText, description, channelId);
        showNotification(id, notificationBuilder);
        return this;
    }

    private NotificationCompat.Builder createNotificationBuilder(String CHANNEL_ID, int icon) {
        return new NotificationCompat.Builder(activity, CHANNEL_ID)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);
    }

    private void showNotification(int notificationId, NotificationCompat.Builder mBuilder) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(activity);
        notificationManager.notify(notificationId, mBuilder.build());
    }

    private void createNotificationIntent() {
        Intent intent = new Intent(activity, targetClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent.getActivity(activity, 0, intent, 0);
    }

    private void createNotificationChannel(String name, String description, String CHANNEL_ID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = activity.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
