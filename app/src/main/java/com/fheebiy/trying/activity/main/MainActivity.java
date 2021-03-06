package com.fheebiy.trying.activity.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.fheebiy.trying.R;
import com.fheebiy.trying.fragment.*;
import com.fheebiy.trying.fragment.scrollable.HomeScrollableFragment;
import com.jaeger.library.StatusBarUtil;

/**
 * Created by bob zhou on 14-8-12.
 *
 * 首页的研究，基本的
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private FrameLayout frameLayout;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    private HomeScrollableFragment twoFragment;
    private TabThreeFragment threeFragment;
    private TabFourFragment fourFragment;
    private TabFiveFragment fiveFragment;


    private TextView btn1;
    private TextView btn2;
    private TextView btn3;
    private TextView btn4;

    private int currentIndex = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
        bindListener();
        init();
        //setStatusBar();
    }


    public void findViews(){
        frameLayout = (FrameLayout) findViewById(R.id.main_frame_layout);
        btn1 = (TextView)findViewById(R.id.main_btn1);
        btn2 = (TextView)findViewById(R.id.main_btn2);
        btn3 = (TextView)findViewById(R.id.main_btn3);
        btn4 = (TextView)findViewById(R.id.main_btn4);
    }

    private void bindListener(){
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    public void init(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        twoFragment = new HomeScrollableFragment();
        threeFragment  = new TabThreeFragment();
        fourFragment = new TabFourFragment();
        fiveFragment = new TabFiveFragment();
        fragmentTransaction.add(R.id.main_frame_layout, threeFragment);
        fragmentTransaction.add(R.id.main_frame_layout, fourFragment);
        fragmentTransaction.add(R.id.main_frame_layout, fiveFragment);
        fragmentTransaction.add(R.id.main_frame_layout, twoFragment);
        //fragmentTransaction.show(twoFragment);
        fragmentTransaction.commit();
        showFragment(twoFragment, threeFragment, fourFragment, fiveFragment);
    }

    private void showFragment(Fragment fragment,Fragment hideFrg1,Fragment hideFrg2,Fragment hideFrg3){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(hideFrg1);
        fragmentTransaction.hide(hideFrg2);
        fragmentTransaction.hide(hideFrg3);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View view) {
        rest();
        switch (view.getId()){
            case R.id.main_btn1:
                btn1.setTextColor(getResources().getColor(R.color.orange));
                showFragment(twoFragment,threeFragment,fourFragment, fiveFragment);
                twoFragment.smoothBackToUp();
                break;
            case R.id.main_btn2:
                btn2.setTextColor(getResources().getColor(R.color.orange));
                showFragment(threeFragment,twoFragment,fourFragment,fiveFragment);
                break;
            case R.id.main_btn3:
                btn3.setTextColor(getResources().getColor(R.color.orange));
                showFragment(fourFragment,twoFragment,threeFragment,fiveFragment);
                break;
            case R.id.main_btn4:
                btn4.setTextColor(getResources().getColor(R.color.orange));
                showFragment(fiveFragment,twoFragment,threeFragment,fourFragment);
                break;
            default:
                break;
        }

    }

    private void rest(){
        btn1.setTextColor(getResources().getColor(R.color.white));
        btn2.setTextColor(getResources().getColor(R.color.white));
        btn3.setTextColor(getResources().getColor(R.color.white));
        btn4.setTextColor(getResources().getColor(R.color.white));
    }


    public void setStatusBar(View view) {
        //StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));

        StatusBarUtil.setTransparentForImageViewInFragment(this, view);
    }
}