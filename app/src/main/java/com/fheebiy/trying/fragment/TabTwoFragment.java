package com.fheebiy.trying.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fheebiy.trying.R;
import com.fheebiy.trying.activity.cood.MyAdapter;
import com.fheebiy.trying.fragment.scrollable.BaseRvFragment;
import com.fheebiy.trying.util.CommonUtil;

import java.util.List;

/**
 * Created by bob zhou on 14-7-30.
 */
public class TabTwoFragment extends BaseRvFragment {

    static final String TAG = "TabTwoFragment";

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);
        Log.d(CommonUtil.LOG_TAG_S, "TabTwoFragment onCreateView");

        mRecyclerView = view.findViewById(R.id.recyclerview);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        RecyclerView.Adapter adapter = new MyAdapter(getActivity());

        mRecyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
