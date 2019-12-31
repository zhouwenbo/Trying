package com.fheebiy.trying.fragment.scrollable;

import com.fheebiy.trying.model.Hero;
import com.fheebiy.trying.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created on 2019/3/5.
 *
 * @author bob zhou.
 * Description:
 */
public class ScrollRvAdapter extends MultiTypeAdapter {

    private List<Object> mList = new ArrayList<>();


    public ScrollRvAdapter() {
        setItems(mList);
        register(Header.class, new HeaderViewBinder());
        register(Hero.class, new HeroViewBinder());
    }

    public void init() {
        //mList.add(new Header());
        //mList.add(new Header());
        mList.addAll(CommonUtil.getHeroListData());
        notifyDataSetChanged();
    }


    public void addHeader() {
        mList.add(0, new Header());
        notifyItemInserted(0);
    }

    public void addItem(Hero hero, int index) {
        mList.add(index, hero);
        notifyItemInserted(index);
    }


}
