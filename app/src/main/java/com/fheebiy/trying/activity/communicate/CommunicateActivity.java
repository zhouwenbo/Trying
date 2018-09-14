package com.fheebiy.trying.activity.communicate;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.fheebiy.trying.R;
import com.fheebiy.trying.fragment.TabThreeFragment;
import com.fheebiy.trying.fragment.communicate.CommFragment1;
import com.fheebiy.trying.fragment.communicate.CommFragment2;
import com.fheebiy.trying.model.Hero;
import android.support.v4.app.FragmentManager;
/**
 *
 * Activity fragment 之间的通信
 * Created by bob zhou on 14-9-24.
 * Fragment之间通信，无论采用Broadcast还是Interface方式都必须通过Activity中转，不可直接通信
 * 竟然可以在fragment中直接调用activity的成员方法,所以接口的方式是否显的好笨
 */
public class CommunicateActivity extends FragmentActivity implements CommFragment1.AddOrSubListViewListener,CommFragment1.ChangeTextListener,CommFragment1.SwitchFragListener {

    CommFragment2 fragment2;

    TabThreeFragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communicate);
        init();
    }


    @Override
    public void sub(int position) {
        fragment3.subOne(position);
    }

    @Override
    public void add(Hero hero) {
        fragment3.addOne(hero);
    }

    private void init(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment2 =  (CommFragment2)fragmentManager.findFragmentById(R.id.fragment2);
        fragment3 =  (TabThreeFragment)fragmentManager.findFragmentById(R.id.fragment3);
    }

    @Override
    public void changeText(String text) {
        fragment2.changeText(text);
    }

    @Override
    public void switchFrag(int position) {
        Toast.makeText(this, "position=" + position, Toast.LENGTH_SHORT).show();
    }

    public void directInvokeMethod(String str) {
        Toast.makeText(this, "the str=" + str, Toast.LENGTH_SHORT).show();
    }


}
