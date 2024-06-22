package com.askyr.notificationexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_showNotification;

    private static final String CHANNEL_ID = "notification_demo";
    private static final String CHANNEL_ID1 = "launcherNotification_demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_showNotification = findViewById(R.id.btn_showNotification);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trigger Notification for devices with Android 8.0+ (Oreo and Beyond)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    setUpNotification();
                }
                else {
                    setUpNotifications_oldDevices();
                }
            }
        };

        btn_showNotification.setOnClickListener(onClickListener);
    }

    // This method pops notifications for devices with ANDROID O and later
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setUpNotification() {

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channel to show various properties of Notification.Builder class
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Standard Notifications", NotificationManager.IMPORTANCE_MAX);

        // New notification channel for launching the parent app through the notification
        NotificationChannel launcherNotificationChannel = new NotificationChannel(CHANNEL_ID1, "Launcher Notification", NotificationManager.IMPORTANCE_MAX);

        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setContentTitle("<Notification Title>")                                                                                // setting notification title
                .setContentText("<Notification Body Text>")                                                                             // setting notification body
//                .setStyle(new Notification.BigTextStyle().bigText("<Extended Context Message for the notification body>"))              // extendable style notification with large message
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)                                                                  // setting icon for notification
                .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.lab))
                        .bigLargeIcon((Bitmap) null))                                                                                   // showing a large image through notification
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.lab))                                             // setting the large icon same as large image
                .setOngoing(true)                                                                                                       // used for non retractable notifications
                .setChannelId(CHANNEL_ID);

        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1, builder.build());

        Intent launcherIntent = new Intent(MainActivity.this, MainActivity.class);
        launcherIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        Notification.Builder builder1 = new Notification.Builder(getApplicationContext())
                .setContentTitle("Launcher Notification")
                .setContentText("Click this notification to launch the parent application")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentIntent(PendingIntent.getActivity(getApplicationContext(), 1, launcherIntent, 0))
                .setOngoing(false)
                .setChannelId(CHANNEL_ID1);

        notificationManager.createNotificationChannel(launcherNotificationChannel);
        notificationManager.notify(2, builder1.build());
    }

    // This method pops notifications for devices with SDK version before ANDROID O
    private void setUpNotifications_oldDevices() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setContentTitle("<Notification Title>")                                                                                // setting notification title
                .setContentText("<Notification Body Text>")                                                                             // setting notification body
//                .setStyle(new Notification.BigTextStyle().bigText("<Extended Context Message for the notification body>"))              // extendable style notification with large message
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)                                                                  // setting icon for notification
                .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.lab))
                        .bigLargeIcon((Bitmap) null))                                                                                   // showing a large image through notification
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.lab))                                             // setting the large icon same as large image
                .setOngoing(true);                                                                                                  // used for non retractable notifications

        notificationManager.notify(1, builder.build());

        Intent launcherIntent = new Intent(MainActivity.this, MainActivity.class);
        launcherIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        Notification.Builder builder1 = new Notification.Builder(getApplicationContext())
                .setContentTitle("Launcher Notification")
                .setContentText("Click this notification to launch the parent application")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentIntent(PendingIntent.getActivity(getApplicationContext(), 1, launcherIntent, 0))
                .setOngoing(false);

        notificationManager.notify(2, builder1.build());
    }
}