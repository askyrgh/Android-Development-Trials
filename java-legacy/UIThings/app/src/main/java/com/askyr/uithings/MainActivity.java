package com.askyr.uithings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;  // TextView object for referencing the component from UI
    EditText editText;  // EditText object for referencing the component from UI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txt1);     // locating the TextView component by id
        editText = findViewById(R.id.edtInput); // locating the EditText component by id
    }

    public void gotToSecondActivity() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);    // intent for switching to second activity
        intent.putExtra("some_input", editText.getText().toString());   // adding string to intent for retrieval in second activity
        startActivity(intent);
    }

    public void animate(View view) {
        // animate functions
        textView.animate().rotationX(120).start();
        textView.animate().alpha(0).setDuration(1500).start();
        textView.animate().scaleY(1.25f).scaleX(1.25f).setDuration(500).start();
        textView.animate().translationY(-100).start();

        gotToSecondActivity();
    }
}