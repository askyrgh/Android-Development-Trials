package com.askyr.customviews;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FirstCustomView customView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customView = findViewById(R.id.exm1_FirstCustomView);
        textView = findViewById(R.id.txt_dummy);

        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked FirstCustomView exm1", Toast.LENGTH_SHORT).show();
            }
        });

        View.OnClickListener btnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(getResources().getColor(R.color.teal_700));
                textView.setText("You clicked Button-1 of Custom View");
            }
        };
        
        customView.setButton1onClickListener(btnClickListener);
    }
}