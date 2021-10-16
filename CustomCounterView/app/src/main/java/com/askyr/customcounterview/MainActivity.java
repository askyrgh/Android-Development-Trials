package com.askyr.customcounterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CounterView counterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterView = findViewById(R.id.counter_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        counterView.setProgressAndText("66");
    }

}