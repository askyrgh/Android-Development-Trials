package com.askyr.threadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnClick, btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = findViewById(R.id.btnClick);
        btnDownload = findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating a new thread to execute the for-loop
                Thread bgThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Running the following code on main thread(UI) will cause it to suspend until for-loop completes
                        // This will probably lead to app crashing
                        for(int i=0; i<25; i++) {
                            try {
                                // This will put the current thread(UI thread here) to sleep(basically pause) for 1s
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Log.d("Output", "Inside for-loop " + i);
                        }
                    }
                });

                bgThread.start();
            }
        });

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Check logs on Logcat", Toast.LENGTH_SHORT).show();
            }
        });
    }
}