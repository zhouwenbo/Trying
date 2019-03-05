package com.fheebiy.trying.fragment.scrollable;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fheebiy.trying.R;
import com.fheebiy.trying.model.Hero;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created on 2019/3/5.
 *
 * @author bob zhou.
 * Description:
 */
public class HeroViewBinder extends ItemViewBinder<Hero, HeroViewBinder.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.home_rv_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, @NonNull Hero item) {
        viewHolder.mTextView.setText(item.getName());
        ViewGroup.LayoutParams params = viewHolder.itemView.getLayoutParams();
        params.height = 400;
        viewHolder.itemView.setLayoutParams(params);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.card_tv);
        }
    }
}
