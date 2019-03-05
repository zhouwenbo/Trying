package com.fheebiy.trying.fragment.scrollable;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
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
public class MyScrollableFrameLayout3 extends FrameLayout {

    private View mLinearLayout;

    private MarginLayoutParams mMarginLayoutParams;

    private int mStartTopMargin;

    private int mEndTopMargin;
    private int mDownTopMaigin;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    // 分别记录上次滑动的坐标(onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private ScrollListner mScrollListner;

    public static final String TAG = "MyScrollableF";

    /**
     * 滑动的临界值
     */
    private int mTouchSlop;

    public MyScrollableFrameLayout3(Context context) {
        super(context);
        init();
    }

    public MyScrollableFrameLayout3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyScrollableFrameLayout3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = false;
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                intercepted = false;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;

                int currentMargin = mMarginLayoutParams.topMargin;
                Log.d(TAG, "DY = " + Math.abs(deltaY) + " ,Math.abs(deltaX) = " + Math.abs(deltaX));
                if (Math.abs(deltaY) > Math.abs(deltaX)) {
                    Log.d(TAG, "yMove = " + deltaY + "currentMargin = " + currentMargin);
                    //从上往下滑动
                    if (deltaY > 0) {
                        if (currentMargin >= mEndTopMargin && currentMargin < mStartTopMargin) {
                            intercepted = true;
                        }
                    } else {
                        if (currentMargin > mEndTopMargin && currentMargin <= mStartTopMargin) {
                            intercepted = true;
                        }
                    }

                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                break;
            }
            default:
                break;
        }

        Log.d(TAG, "intercepted=" + intercepted);
        mLastX = x;
        mLastY = y;
        mLastXIntercept = x;
        mLastYIntercept = y;

        return intercepted;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                //scrollBy(-deltaX, 0);

                if (mMarginLayoutParams.topMargin <= mStartTopMargin && mMarginLayoutParams.topMargin >= mEndTopMargin) {
                    int currentMargin = mMarginLayoutParams.topMargin + deltaY;
                    if (currentMargin >= mStartTopMargin) {
                        currentMargin = mStartTopMargin;
                    }

                    if (currentMargin <= mEndTopMargin) {
                        currentMargin = mEndTopMargin;
                    }

                    mMarginLayoutParams.topMargin = currentMargin;
                    mLinearLayout.setLayoutParams(mMarginLayoutParams);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {

                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
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
