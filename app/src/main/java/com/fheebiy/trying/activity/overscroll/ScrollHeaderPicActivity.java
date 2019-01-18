package com.fheebiy.trying.activity.overscroll;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fheebiy.trying.R;
import com.fheebiy.trying.util.Log;
import com.jaeger.library.StatusBarUtil;

public class ScrollHeaderPicActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private AppBarLayout mAppBarLayout;

    private View mTitleAphaView;
    private Toolbar mToolbar;

    public static final String TAG = "ScrollHeaderPicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_header_pic);
        mAppBarLayout = findViewById(R.id.app_bar);
        mTitleAphaView = findViewById(R.id.title_alpha_view);
        mToolbar = findViewById(R.id.toolbar);

        mAppBarLayout.addOnOffsetChangedListener(this);
        StatusBarUtil.setTransparentForImageView(this, mToolbar);
        //test();

    }

    private void test() {

        int color = getResources().getColor(R.color.white);
        StatusBarUtil.setColor(this, color, 0);
        StatusBarUtil.setLightMode(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int offset = Math.abs(verticalOffset);
        //最大偏移距离
        int scrollRange = appBarLayout.getTotalScrollRange();
        if (scrollRange == 0) {
            return;
        }
        float f = (float) offset / scrollRange;

        mTitleAphaView.setAlpha(f);



        if (f == 1) {
            test();
        } else {
            int alpha = (int) (f*255);
            StatusBarUtil.setTranslucentForImageView(this, alpha, mToolbar);
            Log.d(TAG, "alpha = " + alpha);
        }

        //StatusBarUtil.setColor(this, 0xffffff, alpha);
        //StatusBarUtil.setTranslucentForImageView(this, alpha, mToolbar);

    }
}
