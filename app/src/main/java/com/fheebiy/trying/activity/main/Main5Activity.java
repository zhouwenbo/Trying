package com.fheebiy.trying.activity.main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import com.fheebiy.trying.R;
import com.fheebiy.trying.activity.animation.TweenAnimationActivity;
import com.fheebiy.trying.activity.basic.HandlerActivity;
import com.fheebiy.trying.activity.broadcast.BroadcastActivity;
import com.fheebiy.trying.activity.camera.CameraActivity;
import com.fheebiy.trying.view.CustomTabHost;

/**
 * Created by zhouwenbo on 15/8/25.
 * 主页面的另外尝试,最大的不同是采用Activity代替Fragment
 */
public class Main5Activity extends TabActivity {

    /**
     * mTabHost
     */
    private CustomTabHost mTabHost;

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.sy_selector, R.drawable.msg_selector, R.drawable.discovery_selector,
            R.drawable.my_selector};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"搜悦", "消息", "发现", "我的"};

    private String[] tags = {"sy", "msg", "dis", "me"};

    private Class<?>[] mTabActivityArray = {CameraActivity.class, HandlerActivity.class, BroadcastActivity.class, TweenAnimationActivity.class};

    private LayoutInflater layoutInflater;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5);
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (CustomTabHost) getTabHost();
        initTabs();

    }

    private void initTabs() {
        int count = mTabActivityArray.length;
        for (int i = 0; i < count; i++) {
            Intent intent = new Intent(this, mTabActivityArray[i]);
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tags[i]).setIndicator(getTabItemView(i)).setContent(intent);
            mTabHost.addTab(tabSpec);
        }
    }


    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }

}