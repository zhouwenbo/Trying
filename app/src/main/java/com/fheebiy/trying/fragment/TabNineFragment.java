package com.fheebiy.trying.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.fheebiy.trying.R;
import com.fheebiy.trying.adapter.HeroLvAdapter;
import com.fheebiy.trying.util.CommonUtil;

/**
 * Created by zhouwenbo on 15/5/23.
 */
public class TabNineFragment extends BaseFragment {

    private ListView listView;
    private HeroLvAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab9, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView)view.findViewById(R.id.listview_layout);
        adapter = new HeroLvAdapter(getActivity());
        listView.setAdapter(adapter);
        adapter.setList(CommonUtil.getHeroListData());
        //listView.requestDisallowInterceptTouchEvent(true);
    }

    public ListView getListView() {
        return listView;
    }
}