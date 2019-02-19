package com.fheebiy.trying.activity.cood;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fheebiy.trying.R;

import java.util.List;

/**
 * Created on 2019/2/19.
 *
 * @author bob zhou.
 * Description:
 */
public class HRvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HRvAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.card_tv, item);
    }
}
