package com.askyr.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText edtUsername, edtEmail, edtPassword;
    private Button btnSubmit;
    private TextView txtLoginInfo;

    private boolean isSigningUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnSubmit = findViewById(R.id.btnSubmit);

        txtLoginInfo = findViewById(R.id.txtLoginInfo);

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            // If user is already logged-in redirect them to friends activity without authentication activity
            startActivity(new Intent(MainActivity.this, FriendsActivity.class));
            finish();
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtEmail.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
                    // Checking for empty fields
                    if(isSigningUp && edtUsername.getText().toString().isEmpty()) {
                        // Specific case check for SignUp mode
                        Toast.makeText(MainActivity.this, "Invalid input detected!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Unable to Log In due to invalid input!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(isSigningUp) {
                    // Use the button for SignUp if it is SignUp mode
                    handleSignUp();
                }
                else {
                    // Use the button for LogIn if it is not SignUp mode
                    handleLogIn();
                }
            }
        });

        txtLoginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSigningUp) {
                    // if isSigningUp is enabled(which it is) then on clicking the text
                    // it will change the authentication page to LogIn page from SignUp
                    isSigningUp = false;
                    edtUsername.setVisibility(View.GONE);
                    btnSubmit.setText("Log In");
                    txtLoginInfo.setText("Don't have an account? Sign Up");
                }
                else {
                    // if isSigningUp is disabled then on clicking the text
                    // it will change the authentication page back to SignUp page
                    isSigningUp = true;
                    edtUsername.setVisibility(View.VISIBLE);
                    btnSubmit.setText("Sign Up");
                    txtLoginInfo.setText("Already have an account? Log In");
                }
            }
        });
    }

    private void handleSignUp() {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            // on successful sign-up we are redirected to Friends Activity
                            startActivity(new Intent(MainActivity.this, FriendsActivity.class));
                            Toast.makeText(MainActivity.this, "Signed Up Successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void handleLogIn() {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            // on successful log-in we are redirected to Friends Activity
                            startActivity(new Intent(MainActivity.this, FriendsActivity.class));
                            Toast.makeText(MainActivity.this, "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}