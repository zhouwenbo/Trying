package com.fheebiy.trying.fragment.scrollable;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.fheebiy.trying.R;
import com.fheebiy.trying.util.CommonUtil;
import com.fheebiy.trying.util.Log;

/**
 * Created on 2019/3/5.
 *
 * @author bob zhou.
 * Description:
 */
public class MyScrollableFrameLayout4 extends FrameLayout {

    private View mLinearLayout;

    private MarginLayoutParams mMarginLayoutParams;

    private int mStartTopMargin;

    private int mEndTopMargin;
    private float mYDown;
    private float mXDown;
    private int mDownTopMaigin;

    private float mInterceptXDown;
    private float mInterceptYDown;

    private ScrollListner mScrollListner;

    public static final String TAG = "My_ScrollableFra_";

    /**
     * 滑动的临界值
     */
    private int mTouchSlop;

    public MyScrollableFrameLayout4(Context context) {
        super(context);
        init();
    }

    public MyScrollableFrameLayout4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyScrollableFrameLayout4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    public void setScrollListner(ScrollListner listner) {
        this.mScrollListner = listner;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLinearLayout = findViewById(R.id.anim_viewpager);
        mMarginLayoutParams = (MarginLayoutParams) mLinearLayout.getLayoutParams();
        mStartTopMargin = CommonUtil.dip2px(getContext(), 80);  //240
        mEndTopMargin = CommonUtil.dip2px(getContext(), 46);    //138
        Log.d(TAG, "start = " + mStartTopMargin + " ,end = " + mEndTopMargin);

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mYDown = event.getY();
                mXDown = event.getX();
                mDownTopMaigin = mMarginLayoutParams.topMargin;
                break;

            case MotionEvent.ACTION_MOVE:

                float currentY = event.getY();
                float currentX = event.getX();
                int yMove = (int) (currentY - mYDown);
                int xMove = (int) (currentX - mXDown);

                final int yDiff = Math.abs(yMove);
                final int xDiff = Math.abs(xMove);
                boolean isVerticalScroll = yDiff > xDiff && yDiff > mTouchSlop;
                boolean isCeling = mScrollListner.isCeiling();
                //Log.d(TAG, "yDiff = " + yDiff + " ,xDiff = " + xDiff);
                if (isCeling) {
                    Log.e(TAG, "isVerticalScroll = " + isVerticalScroll + " ,yMove = " + yMove + " ,isCeling = " + isCeling);
                } else {
                    Log.d(TAG, "isVerticalScroll = " + isVerticalScroll + " ,yMove = " + yMove + " ,isCeling = " + isCeling);
                }

                if (isVerticalScroll && isCeling) {
                    if (mMarginLayoutParams.topMargin <= mStartTopMargin && mMarginLayoutParams.topMargin >= mEndTopMargin) {
                        int currentMargin = mDownTopMaigin + yMove;
                        if (currentMargin >= mStartTopMargin) {
                            currentMargin = mStartTopMargin;
                        }

                        if (currentMargin <= mEndTopMargin) {
                            currentMargin = mEndTopMargin;
                        }

                        mMarginLayoutParams.topMargin = currentMargin;
                        mLinearLayout.setLayoutParams(mMarginLayoutParams);

                        return true;
                    }
                }

                break;

            case MotionEvent.ACTION_UP:
                break;

            default:

        }


        return super.dispatchTouchEvent(event);
    }

    public interface ScrollListner {

        /**
         * 是否吸顶
         *
         * @return
         */
        boolean isCeiling();

        /**
         * 是否RecyclerView滑动到顶部
         *
         * @return
         */
        boolean isCanScroll();

    }
}
