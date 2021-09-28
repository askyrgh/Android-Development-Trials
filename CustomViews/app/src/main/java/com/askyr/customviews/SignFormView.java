package com.askyr.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class SignFormView extends LinearLayout {
    public SignFormView(Context context) {
        super(context);
        init();
    }

    public SignFormView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SignFormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }
}
