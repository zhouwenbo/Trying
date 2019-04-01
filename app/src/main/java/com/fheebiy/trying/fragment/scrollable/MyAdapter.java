package com.fheebiy.trying.fragment.scrollable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fheebiy.trying.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/12/18.
 *
 * @author bob zhou.
 * Description:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> list = new ArrayList<>();
    private List<Integer> heights = new ArrayList<>();
    private Context mContext;

    public MyAdapter(Context c) {
        for (int i = 0; i < 20; i++) {
            list.add("index = " + i);
            heights.add((int) (200 + Math.random() * 400));
        }
        this.mContext = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itmeView = LayoutInflater.from(mContext).inflate(R.layout.home_rv_item, viewGroup, false);
        return new ViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(list.get(i));
        ViewGroup.LayoutParams params = viewHolder.itemView.getLayoutParams();
        params.height = heights.get(i);
        viewHolder.itemView.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.card_tv);
        }
    }
}
