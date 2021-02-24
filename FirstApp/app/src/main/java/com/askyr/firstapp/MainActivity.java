package com.askyr.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNumberOne, edtNumberTwo;  // editing of text on UI is enabled from here
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumberOne = findViewById(R.id.numberOne);   // finding the EditText UI element by it's id
        edtNumberTwo = findViewById(R.id.numberTwo);   // finding the EditText UI element by it's id
        txtResult = findViewById(R.id.result);
    }

    public void showToastMessage(View view) {
        String firstNumber = edtNumberOne.getText().toString();    // second number
        String secondNumber = edtNumberTwo.getText().toString();   // first number

        String message;

        if(firstNumber.isEmpty() || secondNumber.isEmpty()) // condition to avoid crash due to empty string
        {
            message = "Wrong Input";
        }
        else
        {
            int resultValue = Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber);   //Multiplying both the numbers
            message = "Result is " + resultValue;
            txtResult.setText(String.valueOf(resultValue));    // updating the TextView
        }

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}