package com.askyr.eatmore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout cl_Welcome;
    private TextView txt_LogIn;
    private TextView txt_SignUp;
    private TextView txt_ForgotPassword;
    private Button btn_Submit;
    private TextInputLayout til_ConfirmPassword;
    private TextInputEditText edt_ConfirmPassword;

    private boolean onSignUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_EatMore);
        setContentView(R.layout.activity_main);

        cl_Welcome = findViewById(R.id.clWelcome);
        txt_LogIn = findViewById(R.id.txtLogIn);
        txt_SignUp = findViewById(R.id.txtSignUp);
        txt_ForgotPassword = findViewById(R.id.txtForgotPassword);

        btn_Submit = findViewById(R.id.btnSubmit);

        til_ConfirmPassword = findViewById(R.id.tilConfirmPassword);
        edt_ConfirmPassword = findViewById(R.id.edtConfirmPassword);

        cl_Welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                cl_Welcome.setVisibility(View.GONE);
            }
        });

        txt_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                onSignUp = false;

                txt_LogIn.setBackground(getResources().getDrawable(R.drawable.text_selected));
                txt_LogIn.setTextColor(getResources().getColor(R.color.white));
                txt_LogIn.setElevation(5);

                txt_SignUp.setBackground(getResources().getDrawable(R.drawable.text_unselected));
                txt_SignUp.setTextColor(getResources().getColor(R.color.red));
                txt_SignUp.setElevation(0);

                btn_Submit.setText("Log In");
                txt_ForgotPassword.setVisibility(View.VISIBLE);
                til_ConfirmPassword.setVisibility(View.GONE);
            }
        });

        txt_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                onSignUp = true;

                txt_LogIn.setBackground(getResources().getDrawable(R.drawable.text_unselected));
                txt_LogIn.setTextColor(getResources().getColor(R.color.red));
                txt_LogIn.setElevation(0);

                txt_SignUp.setBackground(getResources().getDrawable(R.drawable.text_selected));
                txt_SignUp.setTextColor(getResources().getColor(R.color.white));
                txt_SignUp.setElevation(5);

                btn_Submit.setText("Sign Up");
                txt_ForgotPassword.setVisibility(View.GONE);
                til_ConfirmPassword.setVisibility(View.VISIBLE);
            }
        });

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (onSignUp) {
                    
                    // Start Sign-Up process
                    Toast.makeText(MainActivity.this, "Signing Up", Toast.LENGTH_SHORT).show();
                }
                else {
                    
                    // Start Log-In process
                    Toast.makeText(MainActivity.this, "Logging In", Toast.LENGTH_SHORT).show();
                }
                Intent homeScreenIntent = new Intent(MainActivity.this, HomeActivity.class);
                
                startActivity(homeScreenIntent);
            }
        });
    }
}