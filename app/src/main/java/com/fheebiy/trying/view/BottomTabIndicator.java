package com.fheebiy.trying.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fheebiy.trying.R;

/**
 * Created on 2019/2/20.
 *
 * @author bob zhou.
 * Description:
 */
public class BottomTabIndicator extends LinearLayout implements View.OnClickListener {


    private OnTabSelectedListener mOnTabSelectedListener;

    public BottomTabIndicator(Context context) {
        super(context);
    }


    public BottomTabIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomTabIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        mOnTabSelectedListener = onTabSelectedListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();
        for (int i = 0; i < count; ++i) {
            getChildAt(i).setTag(i);
            getChildAt(i).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        if (mOnTabSelectedListener != null) {
            mOnTabSelectedListener.onTabSelected(tag);
        }

        int count = getChildCount();
        for (int i = 0; i < count; ++i) {
            TextView textView = (TextView) getChildAt(i);
            if ((int) textView.getTag() == tag) {
                textView.setTextColor(getResources().getColor(R.color.orange));
            } else {
                textView.setTextColor(getResources().getColor(R.color.black));
            }
        }
    }

    public interface OnTabSelectedListener {
        void onTabSelected(int i);
    }


}
