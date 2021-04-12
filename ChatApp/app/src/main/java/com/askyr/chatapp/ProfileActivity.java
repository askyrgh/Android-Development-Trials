package com.askyr.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class ProfileActivity extends AppCompatActivity {

    private Button btnLogOut, btnUpload;
    private ImageView imgProfile;
    private TextView txtUserEmail;

    private String myImageURL;
    private String userEmail;

    private Uri imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogOut = findViewById(R.id.btnLogOut);
        btnUpload = findViewById(R.id.btnUploadImage);
        imgProfile = findViewById(R.id.imgProfile);
        txtUserEmail = findViewById(R.id.txtUserEmail);
        myImageURL = getIntent().getStringExtra("profile_image_URL");

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent with a specific action(here Picking an image)
                Intent photoIntent = new Intent(Intent.ACTION_PICK);
                // specifying type for picking during the action(ACTION_PICK)
                // it specifies that we want to open gallery
                photoIntent.setType("image/*");
                // to get image
                startActivityForResult(photoIntent, 1);
            }
        });

        fetchProfileData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // specifying through request code what to do on getting the result through the Intent object
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // extracting the image from activity result
            imagePath = data.getData();

            getImageInImageView();
        }
    }

    private void getImageInImageView() {
        Bitmap sourceBitmap = null;
        try {
            // storing the image from image path obtained from intent
            sourceBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);

            // images appeared rotated(anticlockwise 90deg)
            // therefore to fix it matrix rotation is done
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotatedBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0, sourceBitmap.getWidth(), sourceBitmap.getHeight(), matrix, true);
            sourceBitmap = rotatedBitmap;

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Image not found!", Toast.LENGTH_SHORT).show();
        }

        imgProfile.setImageBitmap(sourceBitmap);
    }

    private void uploadImage() {
        // progress window to show upload status
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        // uploading the image directly from the specified path from gallery(in device storage)
        FirebaseStorage.getInstance().getReference("images/" + UUID.randomUUID().toString()).putFile(imagePath)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        // closing the progress window on successful completion of upload and displaying a toast message
                        if(task.isSuccessful()) {
                            task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        updateProfilePicture(task.getResult().toString());
                                    }
                                }
                            });

                            Toast.makeText(ProfileActivity.this, "Image uploaded!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ProfileActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        // displaying the upload progress
                        double progress = 100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount();
                        progressDialog.setMessage(" Uploaded " +  (int)progress + "%");
                    }
                });
    }

    private void updateProfilePicture(String url) {
        // rewriting the profilePicture value(URL) of the user in the database
        FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/profilePicture").setValue(url);
    }

    private void fetchProfileData() {
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();

        // displaying the profile image fetched from database URL on profile activity
        if (!myImageURL.isEmpty()) {
            Glide.with(ProfileActivity.this).load(myImageURL).error(R.drawable.account_img).placeholder(R.drawable.account_img).into(imgProfile);
        }
        // displaying the email on profile activity
        txtUserEmail.setText(userEmail);
    }
}