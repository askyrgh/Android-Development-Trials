package com.askyr.menutesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
        if(item.getTitle().equals("Info")) {
            goToInfoActivity();
        }
        return true;
    }

    public void goToInfoActivity() {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);
    }
}