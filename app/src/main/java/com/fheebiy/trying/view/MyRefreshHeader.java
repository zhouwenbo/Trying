package com.fheebiy.trying.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.fheebiy.trying.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * Created on 2018/12/20.
 *
 * @author bob zhou.
 * Description:
 */
public class MyRefreshHeader extends RelativeLayout implements RefreshHeader {

    private Context mContext;
    private RetailPullDownAnimationView mRetailPullDownAnimationView;

    public MyRefreshHeader(Context context) {
        super(context);
        mContext = context;
        setMinimumHeight(DensityUtil.dp2px(50));
        LayoutInflater.from(mContext).inflate(R.layout.view_refresh_header, this);
        mRetailPullDownAnimationView = findViewById(R.id.loading_anim);
    }

    @NonNull
    @Override
    public View getView() {

        Log.d("ZWB", "getView");
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

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        Log.d("ZWB", "onStartAnimator");
        mRetailPullDownAnimationView.startAnimation();

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        Log.d("ZWB", "onFinish");
        mRetailPullDownAnimationView.stopAnimation();
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
