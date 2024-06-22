package com.askyr.sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Switch swNotifications; // Switch Object for referencing the Switch component on UI

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swNotifications = findViewById(R.id.switch1);   // locating the switch component on UI

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isNotifyTurnedOn = sharedPreferences.getBoolean("notifications", true); //setting the default value for the check mark boolean as true for the first time

        swNotifications.setChecked(isNotifyTurnedOn);   // setting the switch as checked or not at the start of activity

        // binding callback to the onCheckChange event through code instead of editor
        swNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    // it is checked
                    // notification turned on
                    sharedPreferences.edit().putBoolean("notifications", true).apply();
                }
                else {
                    // it is not checked
                    // notification turned off
                    sharedPreferences.edit().putBoolean("notifications", false).apply();
                }
            }
        });
    }

}