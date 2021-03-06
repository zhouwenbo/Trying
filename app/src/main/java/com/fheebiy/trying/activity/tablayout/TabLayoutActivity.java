package com.fheebiy.trying.activity.tablayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fheebiy.trying.R;
import com.fheebiy.trying.util.CommonUtil;
import com.fheebiy.trying.util.Log;
import com.fheebiy.trying.view.RetailPullDownAnimationView;
import com.fheebiy.trying.view.xtablayout.XTabLayout;

/**
 * Created on 2018/12/19.
 *
 * @author bob zhou.
 * Description:
 */
public class TabLayoutActivity extends AppCompatActivity {

    private static final String TAG = "TabLayoutActivity";
    private TabLayout mTabLayout;

    private XTabLayout mTabLayout2;

    private RetailPullDownAnimationView mAnimationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout2 = findViewById(R.id.tabLayout2);
        for (int i = 0; i < 10; i++) {
            TabLayout.Tab tab = mTabLayout.newTab().setText("Cx" + i);
            mTabLayout.addTab(tab);
        }

        mAnimationView = findViewById(R.id.loading_anim);
        addTab2();

        mTabLayout2.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                Log.d(TAG, "TAB text = " + tab.getTag());
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });

    }

    private void addTab2() {
        for (int i = 0; i < 10; i++) {
            TextView textView = getView2();
            if (i % 2 != 0) {
                textView.setText("日常生活");
            }
            XTabLayout.Tab tab = mTabLayout2.newTab().setCustomView(textView);
            tab.setTag(i);
            mTabLayout2.addTab(tab);
        }

        mTabLayout2.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup viewGroup = (ViewGroup) mTabLayout2.getChildAt(0);
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    LinearLayout linearLayout = (LinearLayout) viewGroup.getChildAt(i);
                    linearLayout.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    linearLayout.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

                    int p = CommonUtil.dip2px(getApplicationContext(), 12.5f);
                    if (i == 0) {
                        linearLayout.setPadding(CommonUtil.dip2px(getApplicationContext(), 8), 0, p, 0);
                    } else {
                        linearLayout.setPadding(p, 0, p, 0);
                    }


                    linearLayout.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                }
            }
        });

    }

    @NonNull
    private TextView getTextView(int i) {
        TextView textView = new TextView(this);
        if (i == 3) {
            textView.setText("条件xxxxxx" + i);
        } else {
            textView.setText("条件" + i);
        }
        textView.setMaxLines(1);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(0, 15, 0, 15);
        textView.setMaxEms(5);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setBackgroundResource(R.drawable.shape_tab2_bg);
        return textView;
    }

    public TextView getView2() {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.view_list_header_selector_tab_text, null);
        return textView;
    }


    @Override
    protected void onPause() {
        super.onPause();
        mAnimationView.stopAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAnimationView.startAnimation();
    }
}
