package com.askyr.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    TextInputEditText edtEmail, edtPassword;
    Button btnLogIn, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        edtEmail = findViewById(R.id.edtEmailLogIn);
        edtPassword = findViewById(R.id.edtPasswordLogIn);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnRegister = findViewById(R.id.btnRegister);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // We are logged in so redirect to main activity

            String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            Toast.makeText(LogInActivity.this, "Logged in as " + user.toString(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LogInActivity.this, MainActivity.class));
            finish();
        }

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtEmail.getText().toString().isEmpty()  && !edtPassword.getText().toString().isEmpty()) {
                    FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        // successful log in attempt redirects us to main activity
                                        startActivity((new Intent(LogInActivity.this, MainActivity.class)));
                                        Toast.makeText(LogInActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(LogInActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(LogInActivity.this, "Empty input fields detected!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogInActivity.this, "Redirecting to SignIn page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LogInActivity.this, SignInActivity.class));
            }
        });

    }
}