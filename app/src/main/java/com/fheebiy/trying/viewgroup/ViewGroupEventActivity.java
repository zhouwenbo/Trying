package com.fheebiy.trying.viewgroup;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.fheebiy.trying.R;
import com.fheebiy.trying.util.Log;

/**
 * Created by zhouwenbo on 15/12/21.
 * ViewGroup 事件分发再理解
 * 依据blog : http://blog.csdn.net/guolin_blog/article/details/9153747
 */
public class ViewGroupEventActivity extends Activity {

    private Button mButton1;

    private Button mButton2;

    private MyLayout mLayout;

    private static final String TAG = "ViewGroupEventActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgroup_event);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mLayout = (MyLayout) findViewById(R.id.my_layout);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button1 clicked");
            }
        });


        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button2 clicked");
            }
        });


        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "myLayout clicked");
                return false;
            }
        });
    }
}
