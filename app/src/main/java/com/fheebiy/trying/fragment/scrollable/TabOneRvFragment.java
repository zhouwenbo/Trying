package com.fheebiy.trying.fragment.scrollable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fheebiy.trying.R;

/**
 * Created on 2019/2/20.
 *
 * @author bob zhou.
 * Description:
 */
public class TabOneRvFragment extends BaseRvFragment{

    private RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_one_rv, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerview);


    }

    @Override
    public RecyclerView getRecyclerView() {
        return null;
    }
}
