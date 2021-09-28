package com.askyr.customviews;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FirstCustomView customView;
    private TextView textView;
    private FrameLayout flContainer;
    private String userEmail;

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

        // Creating a new SSignFormView through Java code only

        flContainer = findViewById(R.id.fl_container);
        SignFormView signFormView = new SignFormView(getBaseContext());

        signFormView.SetOnGetDataClicked(new SignFormView.OnGetDataClicked() {
            @Override
            public void OnButtonClicked(String value) {
                userEmail = value;
                Toast.makeText(MainActivity.this, userEmail + " - got it in activity", Toast.LENGTH_SHORT).show();
                flContainer.removeView(signFormView);
            }
        });

        flContainer.addView(signFormView);


    }
}