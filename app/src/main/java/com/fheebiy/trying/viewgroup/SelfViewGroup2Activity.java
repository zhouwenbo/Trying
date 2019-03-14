package com.fheebiy.trying.viewgroup;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fheebiy.trying.R;
import com.fheebiy.trying.util.CommonUtil;
import com.fheebiy.trying.util.Log;

/**
 * Created on 2019/3/5.
 *
 * @author bob zhou.
 * Description:
 */
public class SelfViewGroup2Activity extends AppCompatActivity {

    private LinearLayout mLinearLayout;

    private ViewGroup.MarginLayoutParams headerLayoutParams;

    public static final String TAG = "SelfViewGroup2Act";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_group_2);
        findViewById(R.id.scroll_header_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scroll();
            }
        });

        mLinearLayout = findViewById(R.id.scroll_layout);
        headerLayoutParams = (ViewGroup.MarginLayoutParams) mLinearLayout.getLayoutParams();
    }

    public void scroll() {

        final int start = CommonUtil.dip2px(this,80);
        int end = CommonUtil.dip2px(this, 46);
        final int dex = start - end;

        ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction =  animation.getAnimatedFraction();
                int top = start - (int) (dex * fraction);
                headerLayoutParams.topMargin = top;
                mLinearLayout.setLayoutParams(headerLayoutParams);
                Log.d(TAG, "TOP = " + top);
            }
        });

        animator.start();
    }
}
