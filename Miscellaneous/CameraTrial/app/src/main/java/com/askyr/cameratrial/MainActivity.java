package com.askyr.cameratrial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    Button button;

    public static final int CAMERA_PERMISSION_CODE = 1;
    public static final int CAMERA_IMAGE_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
        button = findViewById(R.id.btnCamera);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // Camera permission check if SDK version is greater than Android M
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // Using camera not allowed

                        // String array is used because we can ask for multiple permissions together
                        String[] permissions = {Manifest.permission.CAMERA};

                        requestPermissions(permissions, CAMERA_PERMISSION_CODE);
                    } else {
                        // Using camera is allowed

                        takePicture();
                    }
                }
                else {
                    // Camera permissions before Android M were not available and camera was directly available

                    takePicture();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Checking permission result on responding to the permission dialog

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // User now clicked "Allow" and permission is granted

                takePicture();
            }
            else {
                Toast.makeText(this, "App needs permission to access Camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // This callback method lets us access the result of the camera activity

        if (requestCode == CAMERA_IMAGE_CODE) {
            // We are specifically checking for the request code what was passed for taking image

            if (resultCode == RESULT_OK){
                // We have a picture

                // Using the data object to obtain the image as result
                Bitmap imageTaken = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(imageTaken);
            }
        }
    }

    private void takePicture() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_IMAGE_CODE);
    }
}