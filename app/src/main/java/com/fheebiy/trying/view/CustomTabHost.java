package com.fheebiy.trying.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TabHost;

/**
 * 重写TabHost的setCurrentTab方法
 * 
 * @author wangguanghui01
 * 
 */
public class CustomTabHost extends TabHost {
    /** 自定义OnTabChangedlistener */
    private CustomOnTabChangeListener mOnTabChangeListener;
    /** tab数量 */
    private int mTabNumber = 0;

    /**
     * 构造方法
     * 
     * @param context
     *            Context
     */
    public CustomTabHost(Context context) {
        super(context);
    }

    /**
     * 构造方法
     * 
     * @param context
     *            Context
     * @param attrs
     *            属性
     */
    public CustomTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentTab(int index) {
        if (index < 0 || index >= mTabNumber) {
            return;
        }

        if (index == getCurrentTab()) {
            return;
        }

        if (mOnTabChangeListener != null) {
            mOnTabChangeListener.onTabChanged(index);
        }

        super.setCurrentTab(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.TabHost#addTab(android.widget.TabHost.TabSpec)
     */
    @Override
    public void addTab(TabSpec tabSpec) {
        mTabNumber++;
        super.addTab(tabSpec);
    }

    /**
     * Register a callback to be invoked when the selected state of any of the
     * items in this list changes
     * 
     * @param l
     *            The callback that will run
     */
    public void setCustomOnTabChangedListener(CustomOnTabChangeListener l) {
        mOnTabChangeListener = l;
    }

    /**
     * Interface definition for a callback to be invoked when tab changed
     */
    public interface CustomOnTabChangeListener {
        /**
         * 回调方法
         * 
         * @param index index
         */
        void onTabChanged(int index);
    }
}
