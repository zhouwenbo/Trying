package com.fheebiy.trying.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.fheebiy.trying.R;
import com.fheebiy.trying.adapter.ComplexVpAdapter;
import com.fheebiy.trying.adapter.ImagePagerAdapter;
import com.fheebiy.trying.util.CommonUtil;
import com.fheebiy.trying.view.AutoScrollViewPager;
import com.fheebiy.trying.view.ScrollableLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouwenbo on 15/5/25.
 */
public class TabSix2Fragment extends Fragment implements View.OnClickListener, ScrollableLinearLayout.OnScrollYChangeListener {


    private AutoScrollViewPager autoScrollViewPager;

    private List<Integer> imageIdList;


    private ViewPager viewPager;

    private List<Fragment> list;

    private ComplexVpAdapter adapter;

    private TabSevenFragment sevenFragment;
    private Fragment eightFragment;
    private Fragment nineFragment;

    private TextView title1;

    private TextView title2;

    private TextView title3;

    private List<TextView> titles = new ArrayList<TextView>();

    private ImageView strip;

    private int offset;

    private int currIndex;

    protected ScrollableLinearLayout mBannerContainer;
    public static final int TITLE_HEIGHT = 49;
    private float mDensity;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        initRecycleView(view);
        initStripImageView();
        initBannerLayout();
        initUI();
        bindListener();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab6_2, container, false);
    }



    private void findViews(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.anim_viewpager);

        title1 = (TextView) view.findViewById(R.id.anim_title1);
        title2 = (TextView) view.findViewById(R.id.anim_title2);
        title3 = (TextView) view.findViewById(R.id.anim_title3);

        strip = (ImageView) view.findViewById(R.id.animation_strip);

        titles.add(title1);
        titles.add(title2);
        titles.add(title3);

        //mTotalLayout = (FaceLinearLayout) view.findViewById(R.id.main_container);
        mBannerContainer = (ScrollableLinearLayout) view.findViewById(R.id.main_content_banner_container);
        mBannerContainer.setOnScrollListener(this);

    }


    private void initRecycleView(View view) {
        //final View view = LayoutInflater.from(getActivity()).inflate(R.layout.tab6_banner, null);
        autoScrollViewPager = (AutoScrollViewPager) view.findViewById(R.id.asvierpager);
        imageIdList = new ArrayList<Integer>();
        imageIdList.add(R.mipmap.banner1);
        imageIdList.add(R.mipmap.banner2);
        imageIdList.add(R.mipmap.banner3);
        imageIdList.add(R.mipmap.banner4);
        autoScrollViewPager.setAdapter(new ImagePagerAdapter(getActivity(), imageIdList).setInfiniteLoop(true));
        // autoScrollViewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        autoScrollViewPager.setInterval(2000);
        autoScrollViewPager.startAutoScroll();
        autoScrollViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageIdList.size());

        //viewPager = (ViewPager) view.findViewById(R.id.anim_viewpager);
        title1.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 10);
       /* mTotalLayout.addView(view, 0);
        int screenHeight = CommonUtil.getScreenHeight(getActivity());
        Log.d(TAG, screenHeight);
        int xx = CommonUtil.px2dip(getActivity(), screenHeight+540);
        Log.d(TAG, "xx=" + xx);*/
    }


    private void bindListener() {

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                //  Log.d(TAG, "i=" + i + ",v=" + v + ",i2=" + i2);
            /*
                int next_x = 0;
                int x = (int)(offset*v);
                Log.d(TAG,"x="+x+",current_x="+current_x);
                if (i == currIndex) {
                    next_x = current_x + x;
                } else {
                    next_x = -current_x + x;
                }
                Log.d(TAG,"current_x="+current_x+",next_x="+next_x);
                Animation animation = new TranslateAnimation(current_x, next_x, 0, 0);
                current_x = next_x;
                animation.setFillAfter(true);
                animation.setDuration(100);
                strip.startAnimation(animation);*/
            }

            @Override
            public void onPageSelected(int i) {
                // Log.d(TAG, "current  index=" + i);
                Animation animation = new TranslateAnimation(currIndex * offset, i * offset, 0, 0);
                currIndex = i;
                animation.setFillAfter(true);
                animation.setDuration(300);
                strip.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        title1.setOnClickListener(this);
        title2.setOnClickListener(this);
        title3.setOnClickListener(this);

        // scrollableLinearLayout.setOnScrollListener(this);
    }

    private void initStripImageView() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);      //这句，去掉动画就失效了,很神奇，这是为什么了
        int screenWidth = dm.widthPixels;
        offset = screenWidth / 3;
    }

    private void initUI() {
        sevenFragment = new TabSevenFragment();
        eightFragment = new TabEightFragment();
        nineFragment = new TabNineFragment();
        list = new ArrayList<Fragment>();
        list.add(sevenFragment);
        list.add(eightFragment);
        list.add(nineFragment);

        adapter = new ComplexVpAdapter(getActivity().getSupportFragmentManager(), list, getActivity());

        viewPager.setAdapter(adapter);
    }


    private void initBannerLayout(){
        mDensity = getResources().getDisplayMetrics().density;
        mBannerContainer.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                CommonUtil.getScreenHeight(getActivity())-CommonUtil.dip2px(getActivity(), 50)));
        mBannerContainer.setMaxScrollDistance(Math.max(540, 0));
    }


    @Override
    public void onPause() {
        super.onPause();
        // stop auto scroll when onPause
        autoScrollViewPager.stopAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
        // start auto scroll when onResume
        autoScrollViewPager.startAutoScroll();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.anim_title1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.anim_title2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.anim_title3:
                viewPager.setCurrentItem(2);
                break;


        }
    }

    @Override
    public boolean canScrollUp() {
        return mBannerContainer.getScrollY() < mBannerContainer.getMaxScrollDistance();
    }

    @Override
    public boolean canScrollDown() {
        int scrollY = mBannerContainer.getScrollY();
        boolean canScrollDown = scrollY > 0 && scrollY < mBannerContainer.getMaxScrollDistance();

        int index = viewPager.getCurrentItem();
        if (index >= 0) {
            Fragment fragment = adapter.getItem(index);
            if (fragment instanceof BaseFragment) {
                BaseFragment commonFragment = (BaseFragment) fragment;
                ListView listView = commonFragment.getListView();
                if (listView != null && listView.getFirstVisiblePosition() == 0 && listView.getChildAt(0) != null
                        && listView.getChildAt(0).getTop() == listView.getPaddingTop()) {
                    canScrollDown = true;
                }
            }
        }
        return canScrollDown;
    }

    @Override
    public ListView getListView() {
        int index = viewPager.getCurrentItem();
        if (index >= 0) {
            Fragment fragment = adapter.getItem(index);
            if (fragment instanceof BaseFragment) {
                BaseFragment commonFragment = (BaseFragment) fragment;
                ListView listView = commonFragment.getListView();
                return listView;
            }
        }
        return null;
    }

    @Override
    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public void onScrollChanged(int scrollY) {

    }
}
