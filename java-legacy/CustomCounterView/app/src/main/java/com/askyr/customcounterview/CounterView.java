package com.askyr.customcounterview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CounterView extends FrameLayout {

    int animationDuration = 2000;

    ProgressBar pgbProgressBar;
    TextView txtProgress;
    LayerDrawable layerDrawable;

    public CounterView(@NonNull Context context) {
        super(context);
        init();
    }

    public CounterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CounterView);

        // binding "progressAndText" attribute from value resource file - "attrs"
        int progress = typedArray.getInteger(R.styleable.CounterView_progressAndText, 0);
        setProgressAndText(String.valueOf(progress));

        // binding "baseArcColor" attribute from value resource file - "attrs"
        int baseArcColor = typedArray.getColor(R.styleable.CounterView_baseArcColor, getResources().getColor(R.color.black));
        setBaseArcColor(baseArcColor);

        // binding "progressArcColor" attribute from value resource file - "attrs"
        int progressArcColor = typedArray.getColor(R.styleable.CounterView_progressArcColor, getResources().getColor(R.color.purple_200));
        setProgressArcColor(progressArcColor);

        // binding "insideCircleColor" attribute from value resource file - "attrs"
        int insideCircleColor = typedArray.getColor(R.styleable.CounterView_insideCircleColor, getResources().getColor(R.color.white));
        setInsideCircleColor(insideCircleColor);
    }

    public CounterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.counter_view, this);

        pgbProgressBar = view.findViewById(R.id.pgb_progressBar);
        txtProgress = view.findViewById(R.id.txt_progress);

        // this returns a reference to the layer drawable resource file - "progress_background"
        layerDrawable = (LayerDrawable) pgbProgressBar.getProgressDrawable();
    }

    public void setText(String text) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, Integer.parseInt(text));

        // setting the duration for transition
        valueAnimator.setDuration(animationDuration);

        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // updating the text value to animation's transition value
                txtProgress.setText(animation.getAnimatedValue().toString());
            }
        };
        valueAnimator.addUpdateListener(animatorUpdateListener);

        // starting the animation sequence
        valueAnimator.start();
    }

    public void setProgress(int progress) {
        ObjectAnimator.ofInt(pgbProgressBar, "progress", progress).setDuration(animationDuration).start();
    }

    public void setProgressAndText(String progress) {
        setText(progress.toString());
        setProgress(Integer.parseInt(progress));
    }

    public void setBaseArcColor(int color) {
        GradientDrawable drawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.baseArc_color);
        drawable.setColor(color);
    }

    public void setProgressArcColor(int color) {
        RotateDrawable drawable = (RotateDrawable) layerDrawable.findDrawableByLayerId(R.id.progressArc_color);
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    public void setInsideCircleColor(int color) {
        GradientDrawable drawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.insideCircle_color);
        drawable.setColor(color);
    }
}
