package com.fheebiy.trying.viewgroup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.fheebiy.trying.util.CommonUtil;

/**
 * Created on 2019/2/22.
 *
 * @author bob zhou.
 * Description: 自己写的可以滑动的ViewGrop
 */
public class MyScrollableLinearLayout extends LinearLayout {


    private int testHeight = 0;

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
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                break;


            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:


                break;
            default:
                break;


        }


        return super.onTouchEvent(event);
    }
}
