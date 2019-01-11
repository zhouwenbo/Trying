package com.fheebiy.trying.activity.tablayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.fheebiy.trying.R;

/**
 * Created on 2018/12/21.
 *
 * @author bob zhou.
 * Description:
 */
public class SelectorPopupWindow extends PopupWindow {

    private Context mContext;

    public SelectorPopupWindow(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    private void initView() {
        View content = LayoutInflater.from(mContext).inflate(R.layout.popup_selector, null);
        setContentView(content);

        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT); //这两句代码缺一不可，必须同时存在
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setAnimationStyle(R.style.AnimationPreview);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        setOutsideTouchable(true);

    }

}
