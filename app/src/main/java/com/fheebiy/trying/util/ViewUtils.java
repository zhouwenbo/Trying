package com.fheebiy.trying.util;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

/**
 * Created on 2018/7/19.
 *
 * @author bob zhou.
 * Description: view工具类
 */
public class ViewUtils {

    private ViewUtils() {

    }

    public static void setGone(View view) {
        setVisibility(view, View.GONE);
    }

    public static void setVisible(View view) {
        setVisibility(view, View.VISIBLE);
    }

    public static void setInvisible(View view) {
        setVisibility(view, View.INVISIBLE);
    }

    public static void setVisibility(View view, int visibility) {
        if (view != null && view.getVisibility() != visibility) {
            view.setVisibility(visibility);
        }
    }

    /**
     * 扩大视图点击区域
     *
     * @param view 视图
     */
    public static void expandClickArea(final View view) {
        final View parent = (View) view.getParent();
        parent.post(new Runnable() {
            @Override
            public void run() {
                final Rect rect = new Rect();
                view.getHitRect(rect);
                rect.top -= 100;
                rect.left -= 100;
                rect.bottom += 100;
                rect.right += 100;
                parent.setTouchDelegate(new TouchDelegate(rect, view));
            }
        });
    }
}
