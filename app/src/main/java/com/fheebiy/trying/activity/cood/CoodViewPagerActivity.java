package com.fheebiy.trying.activity.cood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fheebiy.trying.R;
import com.fheebiy.trying.adapter.ViewPagerAdapter;
import com.fheebiy.trying.fragment.TabFiveFragment;
import com.fheebiy.trying.fragment.TabFourFragment;
import com.fheebiy.trying.fragment.TabThreeFragment;
import com.fheebiy.trying.fragment.TabTwoFragment;
import com.fheebiy.trying.view.MyRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/2/18.
 *
 * @author bob zhou.
 * Description:
 */
public class CoodViewPagerActivity extends AppCompatActivity {

    private ViewPager vp;


    List<Fragment> list = new ArrayList<Fragment>();

    List<String> titles = new ArrayList<String>();

    private TabLayout mTabLayout;


    private SmartRefreshLayout mRefreshLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cood_vp_3);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setRefreshHeader(new MyRefreshHeader(this));
        mRefreshLayout.setEnableOverScrollBounce(false);
        vp = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabLayout);

        Fragment twoFragment = new TabTwoFragment();

        list.add(twoFragment);
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());

        titles.add("one");
        titles.add("two");
        titles.add("three");
        titles.add("four");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list, titles, this);
        vp.setAdapter(adapter);

        mTabLayout.setupWithViewPager(vp);

    }
}
