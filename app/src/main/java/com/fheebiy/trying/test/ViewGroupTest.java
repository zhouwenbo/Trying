package com.fheebiy.trying.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by zhouwenbo on 15/11/20.
 */
public class ViewGroupTest  extends ViewGroup{

    public ViewGroupTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupTest(Context context) {
        super(context);
    }

    public ViewGroupTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
