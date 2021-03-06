package com.fheebiy.trying.activity.cood;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fheebiy.trying.R;
import com.fheebiy.trying.adapter.ViewPagerAdapter;
import com.fheebiy.trying.fragment.TabFiveFragment;
import com.fheebiy.trying.fragment.TabFourFragment;
import com.fheebiy.trying.fragment.TabThreeFragment;
import com.fheebiy.trying.fragment.scrollable.TabTwoFragment;
import com.fheebiy.trying.view.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/2/18.
 *
 * @author bob zhou.
 * Description:
 */
public class VpTestActivity extends AppCompatActivity {


    private ViewPager vp;


    List<Fragment> list = new ArrayList<Fragment>();

    List<String> titles = new ArrayList<String>();

    private XTabLayout mTabLayout;
    private XTabLayout mTabLayout2;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_test);



        vp = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout2 = findViewById(R.id.tabLayout2);

        Fragment twoFragment = new TabTwoFragment();
        Fragment threeFragment = new TabThreeFragment();
        Fragment fourFragment = new TabFourFragment();
        Fragment fiveFragment = new TabFiveFragment();

        list.add(twoFragment);
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());

        titles.add("one");
        titles.add("two");
        titles.add("three");
        titles.add("four");
        titles.add("five");
        titles.add("six");
        titles.add("seven");
        titles.add("eight");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list, titles, this);
        vp.setAdapter(adapter);

        mTabLayout.setupWithViewPager(vp);

        mTabLayout2.setupWithViewPager(vp);


    }
}
