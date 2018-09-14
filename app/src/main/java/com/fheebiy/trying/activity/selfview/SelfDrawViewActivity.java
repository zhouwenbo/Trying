package com.fheebiy.trying.activity.selfview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.fheebiy.trying.R;
import com.fheebiy.trying.view.CounterView;

/**
 * Created by Lenovo on 15-2-9.
 *
 * 自定义View-----计数器-----自绘控件，具体请看CounterView
 * invalidate通知view重绘,view会调用onDraw方法.
 * requestLayout通知重新布局,调用onMeasure onLayout onDraw三个方法
 *
 */
public class SelfDrawViewActivity extends Activity {

    private CounterView mView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_draw_view);
        mView = (CounterView) findViewById(R.id.counterview);
    }


    public void doInvalidate(View button) {
        mView.invalidate();
    }

    public void doRequestLayout(View button) {
        mView.requestLayout();
    }
}