package com.fheebiy.trying.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.androidquery.AQuery;
import com.fheebiy.interfaces.RouterUri;
import com.fheebiy.trying.R;
import com.fheebiy.trying.activity.PullToScaleImg.GestureImageActivity;
import com.fheebiy.trying.activity.PullToScaleImg.PullToScaleImgActivity;
import com.fheebiy.trying.activity.animation.FrameAnimationActivity;
import com.fheebiy.trying.activity.animation.PropertyAnimationActivity;
import com.fheebiy.trying.activity.animation.TweenAnimationActivity;
import com.fheebiy.trying.activity.aquery.AQueryActivity;
import com.fheebiy.trying.activity.async.AsyncActivity;
import com.fheebiy.trying.activity.async.AyncHttpNPEActivity;
import com.fheebiy.trying.activity.basic.*;
import com.fheebiy.trying.activity.bitmap.BitmapCompressActivity;
import com.fheebiy.trying.activity.broadcast.BroadcastActivity;
import com.fheebiy.trying.activity.camera.CameraActivity;
import com.fheebiy.trying.activity.classloader.ClassloaderActivity;
import com.fheebiy.trying.activity.communicate.Communicate2Activity;
import com.fheebiy.trying.activity.communicate.Communicate3Activity;
import com.fheebiy.trying.activity.communicate.CommunicateActivity;
import com.fheebiy.trying.activity.cood.CoodActivity;
import com.fheebiy.trying.activity.cood.CoodViewPagerActivity;
import com.fheebiy.trying.activity.cood.ScrollFragmentTestActivity;
import com.fheebiy.trying.activity.cood.VpTestActivity;
import com.fheebiy.trying.activity.listview.LoadMoreListViewActivity;
import com.fheebiy.trying.activity.listview.PullToRefreshActivity;
import com.fheebiy.trying.activity.lite.LiteHttpActivity;
import com.fheebiy.trying.activity.listview.SwipeRefreshLayoutActivity;
import com.fheebiy.trying.activity.main.*;
import com.fheebiy.trying.activity.listview.SlideToDelLvActivity;
import com.fheebiy.trying.activity.other.ThemeStyleActivity;
import com.fheebiy.trying.activity.overscroll.ImageScaleActivity;
import com.fheebiy.trying.activity.overscroll.ScrollHeaderPicActivity;
import com.fheebiy.trying.activity.overscroll.ScrollTestActivity;
import com.fheebiy.trying.activity.overscroll.ScrollingActivity;
import com.fheebiy.trying.activity.photoview.PhotoViewLauncherActivity;
import com.fheebiy.trying.activity.selfview.SelfDrawViewActivity;
import com.fheebiy.trying.activity.service.RemoteServiceActivity;
import com.fheebiy.trying.activity.service.ServiceMainActivity;
import com.fheebiy.trying.activity.tablayout.TabLayoutActivity;
import com.fheebiy.trying.activity.test.PullToRefreshTestActivity;
import com.fheebiy.trying.activity.vp.VpActivity;
import com.fheebiy.trying.activity.vp.VpAnimationActivity;
import com.fheebiy.trying.activity.vp.VpComplexActivity;
import com.fheebiy.trying.activity.vp.VpStripActivity;
import com.fheebiy.trying.activity.webview.WebViewActivity;
import com.fheebiy.trying.adapter.IndexAdapter;
import com.fheebiy.trying.event.ViewGroupEventActivity;
import com.fheebiy.trying.model.UIModel;
import com.fheebiy.trying.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * created by bob zhou 2014.08.01
 * <p/>
 * 用于测试fragment
 */
@RouterUri(scheme = "http", host = "fheebiy.com", path = "/jump_test")
public class IndexActivity extends FragmentActivity {

    private AQuery aq;

    private ListView listView;

    private IndexAdapter adapter;

    public static final String TAG = "ZWB_MAIN";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        aq = new AQuery(this);
        listView = aq.id(R.id.listView).getListView();
        adapter = new IndexAdapter(this);
        listView.setAdapter(adapter);
        adapter.setList(getUIList());
        bindListener();
    }


    public List<UIModel> getUIList() {
        List<UIModel> list = new ArrayList<UIModel>();
        list.add(new UIModel("to main1", MainActivity.class));
        list.add(new UIModel("Scrolling header activity", ScrollFragmentTestActivity.class));
        list.add(new UIModel("Scrolling", ScrollHeaderPicActivity.class));
        list.add(new UIModel("tablayout", TabLayoutActivity.class));
        list.add(new UIModel("cood viewpager activity", CoodViewPagerActivity.class));
        list.add(new UIModel("viewpager 横向滑动 activity", VpTestActivity.class));
        list.add(new UIModel("cood", CoodActivity.class));
        list.add(new UIModel("to ClassloaderActivity", ClassloaderActivity.class));
        list.add(new UIModel("to AyncHttpNPEActivity", AyncHttpNPEActivity.class));
        list.add(new UIModel("to ViewGroup 事件分发", ViewGroupEventActivity.class));
        list.add(new UIModel("to PhotoView Launcher activity", PhotoViewLauncherActivity.class));
        list.add(new UIModel("to camera activity", CameraActivity.class));
        list.add(new UIModel("to 手势缩放图片 activity", GestureImageActivity.class));
        list.add(new UIModel("to 图片压缩 activity", BitmapCompressActivity.class));
        list.add(new UIModel("to 图片拖动缩放 activity", PullToScaleImgActivity.class));
        list.add(new UIModel("to broadcast activity", BroadcastActivity.class));
        list.add(new UIModel("to handler activity", HandlerActivity.class));
        list.add(new UIModel("to dir and path activity", DirPathActivity.class));
        list.add(new UIModel("to webview activity", WebViewActivity.class));
        list.add(new UIModel("to layout basic activity", ThemeStyleActivity.class));
        list.add(new UIModel("to async task activity", AsyncTaskActivity.class));
        list.add(new UIModel("to combination view", CombinationViewActivity.class));
        list.add(new UIModel("to self draw view", SelfDrawViewActivity.class));
        list.add(new UIModel("to property animation", PropertyAnimationActivity.class));
        list.add(new UIModel("to tween animation", TweenAnimationActivity.class));
        list.add(new UIModel("to frame animation", FrameAnimationActivity.class));
        list.add(new UIModel("to event dispatcher", EventDispatcherActivity.class));
        list.add(new UIModel("to async http", AsyncActivity.class));
        list.add(new UIModel("to remote service", RemoteServiceActivity.class));
        list.add(new UIModel("to service", ServiceMainActivity.class));
        list.add(new UIModel("to aquery", AQueryActivity.class));
        list.add(new UIModel("to lite http", LiteHttpActivity.class));
        list.add(new UIModel("to OverScrollView", ScrollTestActivity.class));
        list.add(new UIModel("to img scale test", ImageScaleActivity.class));
        list.add(new UIModel("to pull to refresh list view by guolin", PullToRefreshActivity.class));
        list.add(new UIModel("to pull to refresh list view by myself", PullToRefreshTestActivity.class));
        list.add(new UIModel("ListView 不能自动扩展测试", LoadMoreListViewActivity.class));
        list.add(new UIModel("to SwipeRefreshLayout", SwipeRefreshLayoutActivity.class));
        list.add(new UIModel("to window", PopupWindowActivity.class));
        list.add(new UIModel("to del list view", SlideToDelLvActivity.class));
        list.add(new UIModel("to communicate3", Communicate3Activity.class));
        list.add(new UIModel("to communicate2", Communicate2Activity.class));
        list.add(new UIModel("to communicate1", CommunicateActivity.class));
        list.add(new UIModel("to main5", Main5Activity.class));
        list.add(new UIModel("to main4", Main4Activity.class));
        list.add(new UIModel("to main3", Main3Activity.class));
        list.add(new UIModel("to main2", Main2Activity.class));
        list.add(new UIModel("to vp strip", VpStripActivity.class));
        list.add(new UIModel("to animation vp", VpAnimationActivity.class));
        list.add(new UIModel("to complex vp", VpComplexActivity.class));
        list.add(new UIModel("to simple vp", VpActivity.class));
//        list.add(new UIModel("to pull main", PullMainActivity.class));

        return list;
    }


    public void bindListener() {
        aq.id(listView).itemClicked(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UIModel model = (UIModel) parent.getAdapter().getItem(position);
                Intent intent = new Intent(IndexActivity.this,model.getCls());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
}
