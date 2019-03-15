package com.fheebiy.trying.activity.cood.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fheebiy.trying.R;
import com.fheebiy.trying.util.Log;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * Created on 2019/3/14.
 *
 * @author bob zhou.
 * Description:
 */
public class CategoryFreshHeader extends RelativeLayout implements RefreshHeader {

    private TextView mTextView;

    public static final String RELASE_STR = "释放查看上一分类";
    public static final String SCROLL_STR = "下滑查看上一分类";

    public static final String TAG = "CategoryFreshHeader";

    public CategoryFreshHeader(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
        setMinimumHeight(DensityUtil.dp2px(50));
        LayoutInflater.from(context).inflate(R.layout.view_category_fresh_header, this);
        mTextView = findViewById(R.id.text);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return  SpinnerStyle.FixedBehind;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        Log.d(TAG, "isDragging = " + isDragging + " , offset = " + offset + " ,percent = " + percent);
        if (isDragging) {
            if (percent > 1) {
                mTextView.setText(RELASE_STR);
            } else {
                mTextView.setText(SCROLL_STR);
            }
        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        return 0;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }
}
