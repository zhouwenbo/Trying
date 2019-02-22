package com.fheebiy.trying.viewgroup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.fheebiy.trying.util.CommonUtil;
import com.fheebiy.trying.util.Log;

/**
 * Created on 2019/2/22.
 *
 * @author bob zhou.
 * Description: 自己写的可以滑动的ViewGrop
 */
public class MyScrollableLinearLayout extends LinearLayout {


    private int testHeight = 0;

    /**
     * 手机按下时的屏幕坐标
     */
    private float mYDown;

    /**
     * 手机当前所处的屏幕坐标
     */
    private float mYMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mYLastMove;

    public static final String TAG = "MyScrollableLinearLayout";

    public MyScrollableLinearLayout(Context context) {
        super(context);
    }

    public MyScrollableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollableLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        testHeight = CommonUtil.dip2px(getContext(), 300);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //这样就可以固定该ViewGroup的高度了， 不论xml设置多高，都可以在这里定死高度
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height + testHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mYDown = event.getY();
                mYLastMove = mYDown;

                break;
            case MotionEvent.ACTION_MOVE:
                mYMove = event.getY();
                int scrolledY = (int) (mYLastMove - mYMove);
                if (getScrollY() + scrolledY + getHeight() > 0) {
                    scrollBy(0, 0);
                    return true;
                }


                Log.d(TAG, "scrolledY = " + scrolledY);
                scrollBy(0, scrolledY);
                mYLastMove = mYMove;
                break;


            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:


                break;
            default:
                break;


        }

        return super.dispatchTouchEvent(event);
    }
}
