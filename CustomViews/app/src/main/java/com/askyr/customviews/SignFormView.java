package com.askyr.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SignFormView extends LinearLayout {

    private EditText edtEmail, edtName, edtUsername, edtPassword;
    private Button btnGetData;
    private OnGetDataClicked onGetDataClicked;

    public SignFormView(Context context) {
        super(context);
        init();
    }

    public SignFormView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // initializing custom view
        init();

        // Fetching attribute value from the xml
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SignFormView);

        String email = attributes.getString(R.styleable.SignFormView_initialEmail);
        String name = attributes.getString(R.styleable.SignFormView_initialName);
        String username = attributes.getString(R.styleable.SignFormView_initialUsername);
        String password = attributes.getString(R.styleable.SignFormView_initialPassword);
        int size = attributes.getInt(R.styleable.SignFormView_customTextSize, 20);

        // updating value on screen
        edtEmail.setText(email);
        edtName.setText(name);
        edtUsername.setText(username);
        edtPassword.setText(password);

        edtEmail.setTextSize(size);
        edtName.setTextSize(size);
        edtUsername.setTextSize(size);
        edtPassword.setTextSize(size);
    }

    public SignFormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.sign_form_view_layout, this);

        // Binding the in-class views with xml-declared views in caller activity
        edtEmail = view.findViewById(R.id.edt_email);
        edtName = view.findViewById(R.id.edt_name);
        edtUsername = view.findViewById(R.id.edt_username);
        edtPassword = view.findViewById(R.id.edt_password);
        btnGetData = view.findViewById(R.id.btn_getData);

        // Binding the onClickListener for the in-class ButtonView with xml-declared ButtonView and fetching values for attributes
        btnGetData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onGetDataClicked != null) {
                    onGetDataClicked.OnButtonClicked(
                            edtEmail.getText().toString(),
                            edtName.getText().toString(),
                            edtUsername.getText().toString(),
                            edtPassword.getText().toString()
                    );
                }
            }
        });
    }

    // Binding the interface instance declared in caller activity with the in-class interface instance
    public void SetOnGetDataClicked(OnGetDataClicked onGetDataClicked) {
        this.onGetDataClicked = onGetDataClicked;
    }

    // Interface to implement the method for binding attributes
    public interface OnGetDataClicked {
        void OnButtonClicked(String emailValue, String nameValue, String usernameValue, String passwordValue);
    }
}
