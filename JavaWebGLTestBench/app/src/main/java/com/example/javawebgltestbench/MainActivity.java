package com.example.javawebgltestbench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String URL_MESSAGE = "";

    private String gameURL = "https://builds.frolicx0.de/bingo/index.html";

    private Button launchGameButton;
    private Button launchURLButton;
    private EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchGameButton = findViewById(R.id.btn_launchGame);
        launchURLButton = findViewById(R.id.btn_launchURL);
        urlEditText =  findViewById(R.id.edt_url);

        launchGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast mToast = Toast.makeText(MainActivity.this, "Launching URL", Toast.LENGTH_SHORT);
                mToast.show();
                launchWebView(gameURL);
            }
        });

        launchURLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast mToast = Toast.makeText(MainActivity.this, "Launching URL", Toast.LENGTH_SHORT);
                mToast.show();
                launchWebView(urlEditText.getText().toString());
            }
        });
    }

    private void launchWebView(String requestedURL) {
        Intent launcherIntent = new Intent(this, WebViewLauncherActivity.class).putExtra(URL_MESSAGE, requestedURL);
        startActivity(launcherIntent);
    }
}