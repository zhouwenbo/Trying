package com.fheebiy.trying.fragment.scrollable;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fheebiy.trying.R;
import com.fheebiy.trying.activity.cood.HRvAdapter;
import com.fheebiy.trying.activity.main.MainActivity;
import com.fheebiy.trying.adapter.ImagePagerAdapter;
import com.fheebiy.trying.adapter.ViewPagerAdapter;
import com.fheebiy.trying.util.CommonUtil;
import com.fheebiy.trying.view.AutoScrollViewPager;
import com.fheebiy.trying.view.ScrollableLinearLayoutRv;
import com.fheebiy.trying.view.xtablayout.XTabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouwenbo on 15/5/25.
 */
public class HomeScrollableFragment extends Fragment implements View.OnClickListener, ScrollableLinearLayoutRv.OnScrollYChangeListener, MyScrollableFrameLayout4.ScrollListner {


    public static final String TAG = "HomeScrollableFragment";

    private AutoScrollViewPager autoScrollViewPager;

    private List<Integer> imageIdList;


    private ViewPager viewPager;

    private List<Fragment> list;

    private ViewPagerAdapter adapter;


    List<String> titles = new ArrayList<String>();


    private RecyclerView mHRecyclerView;

    private RecyclerView mHRecyclerView2;

    private View mTextView;

    private TextView mDescTv;

    protected ScrollableLinearLayoutRv mBannerContainer;
    public static final int TITLE_HEIGHT = 49;


    private SmartRefreshLayout mRefreshLayout;

    private XTabLayout mTabLayout;

    private MyScrollableFrameLayout4 mMyScrollableFrameLayout4;
    private ViewGroup.MarginLayoutParams mMarginLayoutParams;


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
        return inflater.inflate(R.layout.fragment_tab6_scrollable, container, false);
    }


    private void findViews(View view) {
        viewPager = view.findViewById(R.id.anim_viewpager);

        mMarginLayoutParams = (ViewGroup.MarginLayoutParams) viewPager.getLayoutParams();

        mTabLayout = view.findViewById(R.id.tabLayout);
        mTextView = view.findViewById(R.id.scroll_test_tv);

        mDescTv = view.findViewById(R.id.tv_desc);

        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        //mRefreshLayout.setRefreshHeader(new MyRefreshHeader(getContext()));
        mRefreshLayout.setEnableOverScrollBounce(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mTextView.getVisibility() == View.VISIBLE) {
                            mTextView.setVisibility(View.GONE);
                        } else {
                            mTextView.setVisibility(View.VISIBLE);
                        }
                        mRefreshLayout.finishRefresh();
                    }
                }, 2000);

            }
        });




        //view.findViewById(R.id.scroll_test_tv).setOnClickListener(this);

        mHRecyclerView = view.findViewById(R.id.h_recyclerview);
        mHRecyclerView2 = view.findViewById(R.id.h_recyclerview2);
        mBannerContainer = view.findViewById(R.id.main_content_banner_container);
        mBannerContainer.setOnScrollListener(this);

        mMyScrollableFrameLayout4 = view.findViewById(R.id.scrollable_framelayout);

        mMyScrollableFrameLayout4.setScrollListener(this);


        updateHRecyclerView();
        updateHRecyclerView2();

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setStatusBar(view.findViewById(R.id.search_view));

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
       /* mTotalLayout.addView(view, 0);
        int screenHeight = CommonUtil.getScreenHeight(getActivity());
        Log.d(TAG, screenHeight);
        int xx = CommonUtil.px2dip(getActivity(), screenHeight+540);
        Log.d(TAG, "xx=" + xx);*/
    }


    private void bindListener() {


    }

    private void initStripImageView() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);      //这句，去掉动画就失效了,很神奇，这是为什么了
        int screenWidth = dm.widthPixels;
    }

    private void initUI() {
        //sevenFragment = new TabSevenFragment();
        //eightFragment = new TabEightFragment();
        //nineFragment = new TabNineFragment();
        list = new ArrayList<Fragment>();
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());


        titles.add("one");
        titles.add("two");
        titles.add("three");

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), list, titles, getActivity());

        viewPager.setAdapter(adapter);

        for (int i = 0; i < list.size(); i++) {
            XTabLayout.Tab tab = mTabLayout.newTab();

            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_view_home_tablayout, null);
            tab.setCustomView(view);

            TextView text = view.findViewById(R.id.home_tab_up_tv);
            text.setText("选项卡" + i);

            TextView textView = view.findViewById(R.id.home_tab_down_tv);
            textView.setText("进行时" + i);

            mTabLayout.addTab(tab);


        }

        viewPager.addOnPageChangeListener(new XTabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new XTabLayout.ViewPagerOnTabSelectedListener(viewPager));


    }


    private void initBannerLayout() {
       /* mBannerContainer.setLayoutParams(new SmartRefreshLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                CommonUtil.getScreenHeight(getActivity()) - CommonUtil.dip2px(getActivity(), 50)));*/
        mBannerContainer.setMaxScrollDistance(CommonUtil.dip2px(getContext(), 180 + 150 + 200));
    }


    private void updateHRecyclerView() {
        mHRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("hello : " + i);
        }
        HRvAdapter adapter = new HRvAdapter(R.layout.hrv_item_view, list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "position = " + position);
            }
        });
        mHRecyclerView.setAdapter(adapter);

    }


    private void updateHRecyclerView2() {
        mHRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("hello : " + i);
        }
        HRvAdapter adapter = new HRvAdapter(R.layout.hrv_item_view, list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "position = " + position);
            }
        });
        mHRecyclerView2.setAdapter(adapter);

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

            case R.id.scroll_test_tv:
                Log.d(TAG, "scroll_test_tv clicked");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean canScrollUp(int from) {
        int y = mBannerContainer.getScrollY();
        int max = mBannerContainer.getMaxScrollDistance();
        boolean b = y < max;

        //Log.d(TAG, "y = " + y + " ,max = " + max + " ,b = " + b + " ,from = " + from);

        return b;
    }

    @Override
    public boolean canScrollDown() {
        int scrollY = mBannerContainer.getScrollY();
        boolean canScrollDown = scrollY > 0 && scrollY < mBannerContainer.getMaxScrollDistance();
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView != null && recyclerView.getChildAt(0) != null) {
            boolean can = recyclerView.canScrollVertically(-1);
            if (!can) {
                canScrollDown = true;
            }
        }
        Log.d(TAG, "canScrollDown = " + canScrollDown);
        return canScrollDown && mMyScrollableFrameLayout4.isTopMarginTop();
    }

    @Override
    public RecyclerView getRecyclerView() {
        int index = viewPager.getCurrentItem();
        if (index >= 0) {
            Fragment fragment = adapter.getItem(index);
            if (fragment instanceof BaseRvFragment) {
                BaseRvFragment commonFragment = (BaseRvFragment) fragment;
                RecyclerView recyclerView = commonFragment.getRecyclerView();
                return recyclerView;
            }
        }
        return null;
    }

    public void smoothBackToUp() {
        mBannerContainer.smoothBackToUp();
    }

    @Override
    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public void onScrollChanged(int scrollY) {
        if (scrollY == 0) {
            mRefreshLayout.setEnableRefresh(true);
        } else if (scrollY > 0) {
            mRefreshLayout.setEnableRefresh(false);
        }

        Log.d(TAG, "scrollY = " + scrollY);

        if (scrollY < mBannerContainer.getMaxScrollDistance()) {
            int count = adapter.getCount();
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    Fragment fragment = adapter.getItem(i);
                    if (fragment instanceof BaseRvFragment) {
                        BaseRvFragment commonFragment = (BaseRvFragment) fragment;
                        RecyclerView recyclerView = commonFragment.getRecyclerView();
                        if (recyclerView != null && recyclerView.getChildAt(0) != null && recyclerView.canScrollVertically(-1)) {
                            recyclerView.scrollToPosition(0);
                        }
                    }
                }
            }
        }

        if (scrollY == mBannerContainer.getMaxScrollDistance()) {

           /* mTabLayout.getLayoutParams().height = CommonUtil.dip2px(getContext(), 46);
            //mTabLayout.requestLayout();

            for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                View view = mTabLayout.getTabAt(i).getCustomView().findViewById(R.id.home_tab_down_tv);
                ViewUtils.setGone(view);
            }*/
        } else if (scrollY < mBannerContainer.getMaxScrollDistance()) {
            /*for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                View view = mTabLayout.getTabAt(i).getCustomView().findViewById(R.id.home_tab_down_tv);
                ViewUtils.setVisible(view);
            }

            mTabLayout.getLayoutParams().height = CommonUtil.dip2px(getContext(), 65);*/
            //mTabLayout.requestLayout();
        }
    }

    @Override
    public boolean isCeiling() {
        boolean is =  mBannerContainer.getScrollY() == mBannerContainer.getMaxScrollDistance();

        RecyclerView recyclerView = getRecyclerView();
        boolean can = true;
        if (recyclerView != null && recyclerView.getChildAt(0) != null) {
            //can = true表示能继续向下滑动，can = false表示不能继续向下滑动，也就是滑动到了顶部
            can = recyclerView.canScrollVertically(-1);
        }


        Log.d(TAG, "isCeiling  is = " + is + " , can = " + can);
        return is && !can;
    }

    @Override
    public boolean isCanScroll() {

        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView != null && recyclerView.getChildAt(0) != null) {
            boolean can = recyclerView.canScrollVertically(-1);
        }


        return false;
    }
}
