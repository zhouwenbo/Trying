package com.fheebiy.trying.activity.rv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fheebiy.trying.R;
import com.fheebiy.trying.fragment.scrollable.ScrollRvAdapter;
import com.fheebiy.trying.model.Hero;


public class RvAniActivity extends AppCompatActivity {

    private TextView mAddTv;

    private RecyclerView mRecyclerView;

    private ScrollRvAdapter mRvAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_ani);
        mAddTv = findViewById(R.id.add_tv);
        mAddTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hero hero = new Hero();
                hero.setName("杨过add");
                hero.setSkill("黯然销魂掌");
                hero.setFrom("神雕侠侣");
                mRvAdapter.addItem(hero, 2);
            }
        });
        mRecyclerView = findViewById(R.id.rv);
        setUpRv();
    }

    private void setUpRv() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        MyItemAnimator defaultItemAnimator = new MyItemAnimator();
        defaultItemAnimator.setAddDuration(300);
        mRecyclerView.setItemAnimator(defaultItemAnimator);

        //ScaleInLeftAnimator scaleInLeftAnimator = new ScaleInLeftAnimator();
        //mRecyclerView.setItemAnimator(scaleInLeftAnimator);


        mRvAdapter = new ScrollRvAdapter();
        mRecyclerView.setAdapter(mRvAdapter);
        mRvAdapter.init();
    }
}
