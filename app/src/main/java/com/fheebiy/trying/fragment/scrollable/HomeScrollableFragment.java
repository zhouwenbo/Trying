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
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fheebiy.trying.R;
import com.fheebiy.trying.activity.cood.HRvAdapter;
import com.fheebiy.trying.activity.main.MainActivity;
import com.fheebiy.trying.adapter.ComplexVpAdapter;
import com.fheebiy.trying.adapter.ImagePagerAdapter;
import com.fheebiy.trying.fragment.BaseFragment;
import com.fheebiy.trying.fragment.TabEightFragment;
import com.fheebiy.trying.fragment.TabNineFragment;
import com.fheebiy.trying.fragment.TabSevenFragment;
import com.fheebiy.trying.fragment.TabTwoFragment;
import com.fheebiy.trying.util.CommonUtil;
import com.fheebiy.trying.view.AutoScrollViewPager;
import com.fheebiy.trying.view.MyRefreshHeader;
import com.fheebiy.trying.view.ScrollableLinearLayoutRv;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouwenbo on 15/5/25.
 */
public class HomeScrollableFragment extends Fragment implements View.OnClickListener, ScrollableLinearLayoutRv.OnScrollYChangeListener {


    public static final String TAG = "HomeScrollableFragment";

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

    private RecyclerView mHRecyclerView;

    private RecyclerView mHRecyclerView2;

    private View mTextView;

    private TextView mDescTv;

    protected ScrollableLinearLayoutRv mBannerContainer;
    public static final int TITLE_HEIGHT = 49;


    private SmartRefreshLayout mRefreshLayout;


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

        title1 = view.findViewById(R.id.anim_title1);
        title2 = view.findViewById(R.id.anim_title2);
        title3 = view.findViewById(R.id.anim_title3);

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
        //mRefreshLayout.setEnableRefresh(true);

        strip = (ImageView) view.findViewById(R.id.animation_strip);

        view.findViewById(R.id.ll_header).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        view.findViewById(R.id.scroll_test_tv).setOnClickListener(this);

        titles.add(title1);
        titles.add(title2);
        titles.add(title3);

        mHRecyclerView = view.findViewById(R.id.h_recyclerview);
        mHRecyclerView2 = view.findViewById(R.id.h_recyclerview2);
        //mTotalLayout = (FaceLinearLayout) view.findViewById(R.id.main_container);
        mBannerContainer = (ScrollableLinearLayoutRv) view.findViewById(R.id.main_content_banner_container);
        mBannerContainer.setOnScrollListener(this);

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

                if (i == 2) {
                    if (mDescTv.getVisibility() == View.VISIBLE) {
                        mDescTv.setVisibility(View.GONE);
                    } else {
                        mDescTv.setVisibility(View.VISIBLE);
                    }
                }
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
        //sevenFragment = new TabSevenFragment();
        //eightFragment = new TabEightFragment();
        //nineFragment = new TabNineFragment();
        list = new ArrayList<Fragment>();
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());
        list.add(new TabTwoFragment());

        adapter = new ComplexVpAdapter(getActivity().getSupportFragmentManager(), list, getActivity());

        viewPager.setAdapter(adapter);
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

        Log.d(TAG, "y = " + y + " ,max = " + max + " ,b = " + b + " ,from = " + from);

        return b;
    }

    @Override
    public boolean canScrollDown() {
        int scrollY = mBannerContainer.getScrollY();
        boolean canScrollDown = scrollY > 0 && scrollY < mBannerContainer.getMaxScrollDistance();

        int index = viewPager.getCurrentItem();
        if (index >= 0) {
            Fragment fragment = adapter.getItem(index);
            if (fragment instanceof BaseRvFragment) {
                BaseRvFragment commonFragment = (BaseRvFragment) fragment;
                RecyclerView recyclerView = commonFragment.getRecyclerView();
                if (recyclerView != null && recyclerView.getChildAt(0) != null
                        && recyclerView.getChildAt(0).getTop() == recyclerView.getPaddingTop()) {
                    canScrollDown = true;
                }
            }
        }
        Log.d(TAG, "canScrollDown = " + canScrollDown);
        return canScrollDown;
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
    }
}
