package com.fheebiy.trying.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.fheebiy.trying.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhoukaifeng(zhoukaifeng@meituan.com) on 17/3/9.
 */

public class RetailPullDownAnimationView extends LinearLayout {
    public static final int DEFAULT_ANIM_DURATION_MILLS = 1000;

    private View mLeft;
    private View mRight;

    private int mOffset;

    private TranslateXReverseAnimation mTranslateXReverseAnimationLeft;
    private TranslateXReverseAnimation mTranslateXReverseAnimationRight;
    private int mAnimDurationMills = DEFAULT_ANIM_DURATION_MILLS;

    public RetailPullDownAnimationView(Context context) {
        super(context);
        init(context, null);
    }

    public RetailPullDownAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(21)
    public RetailPullDownAnimationView(Context context, AttributeSet attrs, int defStyleAttr,
                                       int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public RetailPullDownAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray =
                    context.obtainStyledAttributes(attrs, R.styleable.RetailPullDownAnimationView);
            try {
                if (typedArray.hasValue(R.styleable.RetailPullDownAnimationView_animDurationMills))
                    mAnimDurationMills = typedArray
                            .getInteger(R.styleable.RetailPullDownAnimationView_animDurationMills,
                                    DEFAULT_ANIM_DURATION_MILLS);
            } finally {
                typedArray.recycle();
            }
        }
        setOrientation(HORIZONTAL);

        int size = DensityUtil.dp2px( 8);
        int distance = DensityUtil.dp2px( 10);

        mOffset = size + distance;

        mLeft = new TextView(getContext());
        LayoutParams lp = new LayoutParams(size, size);
        lp.gravity = Gravity.CENTER;
        mLeft.setLayoutParams(lp);
        mLeft.setBackgroundResource(R.drawable.bg_pull_down_dot);
        mLeft.setSelected(false);

        mRight = new TextView(getContext());
        LayoutParams params = new LayoutParams(size, size);
        params.leftMargin = distance;
        params.gravity = Gravity.CENTER;
        mRight.setLayoutParams(params);
        mRight.setBackgroundResource(R.drawable.bg_pull_down_dot);
        mRight.setSelected(true);

        addView(mLeft);
        addView(mRight);
    }

    public void startAnimation() {
        if (mTranslateXReverseAnimationLeft == null) {
            mTranslateXReverseAnimationLeft = new TranslateXReverseAnimation(mOffset);
            mTranslateXReverseAnimationLeft.setRepeatCount(Animation.INFINITE);
            mTranslateXReverseAnimationLeft.setRepeatMode(Animation.RESTART);
            mTranslateXReverseAnimationLeft
                    .setDuration(TimeUnit.MILLISECONDS.toMillis(mAnimDurationMills));

            mTranslateXReverseAnimationRight = new TranslateXReverseAnimation(-mOffset);
            mTranslateXReverseAnimationRight.setRepeatCount(Animation.INFINITE);
            mTranslateXReverseAnimationRight.setRepeatMode(Animation.RESTART);
            mTranslateXReverseAnimationRight
                    .setDuration(TimeUnit.MILLISECONDS.toMillis(mAnimDurationMills));
        }
        Log.d("ZWB", "START ANIMATION...");
        mLeft.startAnimation(mTranslateXReverseAnimationLeft);
        mRight.startAnimation(mTranslateXReverseAnimationRight);
    }

    public void stopAnimation() {
        mLeft.clearAnimation();
        mRight.clearAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    private static class TranslateXReverseAnimation extends Animation {

        private int mDx;

        private TranslateXReverseAnimation(int dx) {
            mDx = dx;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation transformation) {
            final Matrix matrix = transformation.getMatrix();
            matrix.setTranslate(linear(mDx, interpolatedTime), 0);
        }

        private float linear(int dx, float time) {
            if (time > 0.5) {
                return dx * (1 - time) * 2.0f;
            } else {
                return dx * time * 2.0f;
            }
        }
    }
}
