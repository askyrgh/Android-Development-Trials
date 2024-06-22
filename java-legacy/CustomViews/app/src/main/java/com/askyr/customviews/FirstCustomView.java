package com.askyr.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FirstCustomView extends FrameLayout {

    private Button button1;
    private OnClickListener button1onClickListener;

    // Constructor for attribute-less initialization through Java

    public FirstCustomView(@NonNull Context context) {
        super(context);
        init();
    }

    // Constructors for attribute based initialization through XML

    public FirstCustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FirstCustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // This method will be called for initializing, everytime we use this view.

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.first_custom_view_layout, this);
        button1 = findViewById(R.id.btn_button1);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(button1onClickListener != null) {
                    button1onClickListener.onClick(v);
                }
                else {
                    Toast.makeText(getContext(), "Clicked Button-1", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Dynamically binding the on-click listener of the button externally

    public void setButton1onClickListener(OnClickListener button1onClickListener) {
        this.button1onClickListener = button1onClickListener;
    }
}
