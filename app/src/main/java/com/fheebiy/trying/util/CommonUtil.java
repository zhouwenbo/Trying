package com.fheebiy.trying.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.fheebiy.trying.model.Hero;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-10-22.
 *
 */
public final class CommonUtil {


    public static final String LOG_TAG = "COMMON_TAG_G";

    public static final String LOG_TAG_S = "COMMON_TAG_S";

    /**
     * 将dp转化为px
     * @param context context
     * @param dpValue dpValue
     * @return px值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取状态栏高度
     * @param cx context
     * @return 返回数值单位为px
     */
    public static int getTitleBarHeight(Activity cx){
        Rect frame = new Rect();
        cx.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }



    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    public static List<Hero> getHeroListData() {
        List<Hero> heros = new ArrayList<Hero>();

        for (int i = 0; i < 20; i++) {
            Hero hero = new Hero();
            hero.setName("杨过" + i);
            hero.setSkill("黯然销魂掌");
            hero.setFrom("神雕侠侣");
            heros.add(hero);
        }

        return heros;
    }


    public static void toast(Context ctx, String msg){
        Toast.makeText(ctx,msg,Toast.LENGTH_SHORT).show();
    }



    public static List<String> getStringList(){
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            list.add("像风一样自由"+i);
        }

        return list;
    }


    /**
     * 获取sdcard下缓存文件夹
     *
     * @param context ctx
     * @param uniqueName 文件夹的名字
     * @return 获取硬盘缓存目录
     */

    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }


    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        } catch (Exception e) {
            android.util.Log.v("connectivity", e.toString());
        }
        return false;
    }

    /**
     * get Screen Width
     *
     * @param context context
     * @return 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }


    /**
     * get Screen height
     *
     * @param context context
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(
                dm);
        return dm.heightPixels;
    }


    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }



}
