package com.fheebiy.trying.activity.cood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fheebiy.trying.R;
import com.fheebiy.trying.view.MyRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created on 2018/12/18.
 *
 * @author bob zhou.
 * Description:
 */
public class CoodActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;

    private SmartRefreshLayout mRefreshLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cood);

        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setRefreshHeader(new MyRefreshHeader(this));

        mRecyclerView = findViewById(R.id.recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new MyAdapter(this);

        mRecyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }
}
