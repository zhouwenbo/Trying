package com.fheebiy.trying.viewgroup.guolinscroller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fheebiy.trying.R;

/**
 * Created on 2019/2/22.
 *
 * @author bob zhou.
 * Description:
 */
public class GuoLinScrollerActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guolin_scroller);
    }


    @Override
    public void onClick(View v) {
        //必须给子view设置onClick，不然无法滑动，因为事件传递受影响
    }
}
