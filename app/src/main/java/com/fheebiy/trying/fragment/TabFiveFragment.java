package com.fheebiy.trying.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fheebiy.trying.R;
import com.fheebiy.trying.util.CommonUtil;

/**
 * Created by Administrator on 14-8-4.
 */
public class TabFiveFragment  extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(CommonUtil.LOG_TAG, "TabFiveFragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab5, container, false);
        Log.d(CommonUtil.LOG_TAG_S, "TabFiveFragment onCreateView");
        return view;
    }
}
