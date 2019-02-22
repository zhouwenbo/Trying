package com.fheebiy.trying.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by zhouwenbo on 15/12/21.
 * ViewGroup事件分发的测试用例
 *
 */
public class MyLayout extends LinearLayout{
    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //return super.onInterceptTouchEvent(ev);\
        //这个true false返回对事件拦截极其重要,true表示拦截, false表示不拦截,结合Activity实例可以看到很明显的结果
        return true;
    }
}
