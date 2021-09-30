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

    private EditText edtEmail;
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
        int size = attributes.getInt(R.styleable.SignFormView_customTextSize, 20);

        // updating value on screen
        edtEmail.setText(email);
        edtEmail.setTextSize(size);
    }

    public SignFormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.sign_form_view_layout, this);
        edtEmail = view.findViewById(R.id.edt_email);
        btnGetData = view.findViewById(R.id.btn_getData);

        btnGetData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), edtEmail.getText().toString(), Toast.LENGTH_SHORT).show();
                if(onGetDataClicked != null) {
                    onGetDataClicked.OnButtonClicked(edtEmail.getText().toString());
                }
            }
        });
    }

    public void SetOnGetDataClicked(OnGetDataClicked onGetDataClicked) {
        this.onGetDataClicked = onGetDataClicked;
    }

    public interface OnGetDataClicked {
        void OnButtonClicked(String value);
    }
}
