package com.fheebiy.trying.fragment.scrollable;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fheebiy.trying.R;
import com.fheebiy.trying.util.Log;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created on 2019/3/5.
 *
 * @author bob zhou.
 * Description:
 */
public class HeaderViewBinder extends ItemViewBinder<Header, HeaderViewBinder.ViewHolder> {


    public static final String TAG = "HeaderViewBinder";

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itmeView = inflater.inflate(R.layout.trans_header_scroll, parent, false);
        return new ViewHolder(itmeView);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, @NonNull Header item) {
        Log.d(TAG, "onBindViewHolder");
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
