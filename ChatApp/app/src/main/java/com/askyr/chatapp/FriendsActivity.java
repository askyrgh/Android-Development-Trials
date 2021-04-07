package com.askyr.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu will be displayed on clicking the profile menu option
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemProfile) {
            // on pressing the profile(menu item) we are redirected to ProfileActivity
            startActivity(new Intent(FriendsActivity.this, ProfileActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}