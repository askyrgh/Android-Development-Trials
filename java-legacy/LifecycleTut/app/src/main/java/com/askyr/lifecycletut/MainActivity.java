package com.askyr.lifecycletut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("my_log", "onCreate() called");

        btn = findViewById(R.id.btn);

        for(int i=0; i<10; i++) {
            Log.d("for_log_output", "i: " + i);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("my_log", "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("my_log", "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("my_log", "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("my_log", "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("my_log", "onDestroy() called");
    }

    public void btnClicked(View view) {
        Log.d("onClick", "btnClicked() called");
    }

}