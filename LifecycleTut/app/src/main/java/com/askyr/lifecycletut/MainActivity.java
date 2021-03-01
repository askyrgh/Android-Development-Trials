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

        btn = findViewById(R.id.btn);

        for(int i=0; i<10; i++) {
            Log.d("for_log_output", "i: " + i);
        }
    }

    public void btnClicked(View view) {
        Log.d("onClick", "btnClicked() called");
    }

}