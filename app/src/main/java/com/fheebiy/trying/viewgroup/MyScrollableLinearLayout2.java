package com.fheebiy.trying.viewgroup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
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
public class MyScrollableLinearLayout2 extends FrameLayout {

    private View mHeaderView;
    private LinearLayout mLinearLayout;

    private ViewGroup.MarginLayoutParams mMarginLayoutParams;

    private int mStartTopMargin;

    private int mEndTopMargin;
    private float mYDown;
    private float mXDown;
    private int mDownTopMaigin;

    public static final String TAG = "MyScrollableLinearLayout2";

    /**
     * 滑动的临界值
     */
    private int mTouchSlop;

    public MyScrollableLinearLayout2(Context context) {
        super(context);
        init();
    }

    public MyScrollableLinearLayout2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyScrollableLinearLayout2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLinearLayout = findViewById(R.id.scroll_layout);
        mHeaderView = findViewById(R.id.scroll_header_view);
        mMarginLayoutParams = (MarginLayoutParams) mLinearLayout.getLayoutParams();
        mStartTopMargin = CommonUtil.dip2px(getContext(), 65);
        mEndTopMargin = CommonUtil.dip2px(getContext(), 46);
        Log.d(TAG, "start = " + mStartTopMargin + " ,end = " + mEndTopMargin);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "mMarginLayoutParams.topMargin = " + mMarginLayoutParams.topMargin);
        if (mMarginLayoutParams.topMargin <= mStartTopMargin && mMarginLayoutParams.topMargin > mEndTopMargin) {
            Log.d(TAG, "mMarginLayoutParams TRUE");
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
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
                Log.d(TAG, "ACTION_MOVE");

                final int yDiff = Math.abs(yMove);
                final int xDiff = Math.abs(xMove);
                boolean isVerticalScroll = yDiff > xDiff && yDiff > mTouchSlop;
                Log.d(TAG, "isVerticalScroll = " + isVerticalScroll + " ,yMove = " + yMove);
                if (isVerticalScroll) {
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
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;

        }
        return true;
    }
}
