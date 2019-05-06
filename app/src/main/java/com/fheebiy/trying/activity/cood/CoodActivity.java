package com.fheebiy.trying.activity.cood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fheebiy.trying.R;
import com.fheebiy.trying.activity.cood.widget.CategoryFreshHeader;
import com.fheebiy.trying.fragment.scrollable.MyAdapter;
import com.fheebiy.trying.fragment.scrollable.ScrollRvAdapter;
import com.fheebiy.trying.view.MyRefreshHeader;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created on 2018/12/18.
 *
 * @author bob zhou.
 * Description:
 */
public class CoodActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private SmartRefreshLayout mRefreshLayout;

    private TextView mStayTv;

    private ScrollRvAdapter mScrollRvAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cood);

        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setRefreshHeader(new CategoryFreshHeader(this));
        LRecyclerView lRecyclerView;

        mRecyclerView = findViewById(R.id.recyclerview);

        mStayTv = findViewById(R.id.tv_stay);
        mStayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mScrollRvAdapter.addHeader();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mScrollRvAdapter = new ScrollRvAdapter();
        mScrollRvAdapter.init();

        mRecyclerView.setAdapter(mScrollRvAdapter);

        mScrollRvAdapter.notifyDataSetChanged();

    }
}
