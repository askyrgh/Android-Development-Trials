package com.askyr.uithings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    TextView textView;  // TextView object for referencing in this class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.txtSecond);    // locating the TextView by it's id

        String someInput = getIntent().getStringExtra("some_input");    // retrieving the string from main activity
        textView.setText(someInput);    // updating the text inside the TextView in second activity
    }
}