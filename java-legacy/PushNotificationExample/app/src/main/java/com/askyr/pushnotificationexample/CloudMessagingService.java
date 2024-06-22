package com.askyr.pushnotificationexample;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class CloudMessagingService extends FirebaseMessagingService {

    // The token is utilized by FCM when sending push notifications to device
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        Log.d("maid-test", s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("maid-test", "MESSAGE RECEIVED: " + remoteMessage.getData().get("temperature"));

        // Send local notification when the app is running


    }
}
