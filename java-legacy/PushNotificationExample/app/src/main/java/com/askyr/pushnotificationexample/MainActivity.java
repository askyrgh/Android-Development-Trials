package com.askyr.pushnotificationexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "pn-test";

    private Button btnSHD, btnDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSHD = findViewById(R.id.btn_SHD);
        btnDF = findViewById(R.id.btn_DF);

        btnSHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribeTo("ShanghaiDragons");
            }
        });

        btnDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribeTo("DallasFuel");
            }
        });

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d(TAG, token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void subscribeTo(String team) {
        FirebaseMessaging.getInstance().subscribeToTopic(team).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Subscribed to " + team, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Failed subscribing to " + team, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}