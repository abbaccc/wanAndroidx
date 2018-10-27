package com.xdjwan.wan.main.mvp.views;

import android.graphics.Color;

import com.xdjcore.core.bottom.BaseBottomDelegate;
import com.xdjcore.core.bottom.BottomTabBean;
import com.xdjcore.core.bottom.ItemBuilder;
import com.xdjcore.core.fragments.WanAdFragment;
import com.xdjwan.wan.main.mvp.views.home.HomeFragment;
import com.xdjwan.wan.main.mvp.views.knowledge.main.KnowledgeFragment;
import com.xdjwan.wan.main.mvp.views.person.PersonFragment;

import java.util.LinkedHashMap;

/**
 * Created by jx on 2018/5/3.
 */

public class WanBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, WanAdFragment> setItems(ItemBuilder itemBuilder) {
        final LinkedHashMap<BottomTabBean, WanAdFragment> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "首页"), new HomeFragment());
        items.put(new BottomTabBean("{fa-pencil-square-o}", "知识"), new KnowledgeFragment());
        items.put(new BottomTabBean("{fa-buysellads}", "我的"), new PersonFragment());
//        items.put(new BottomTabBean("{fa-leanpub}", "项目"), new ProjectDelegate());
        return itemBuilder.addItems(items).buid();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#00BBFF");
    }


    @Override
    public void initUI() {

    }

    @Override
    public int setTitleBar() {
        return 0;
    }

    @Override
    public int setStatusBarView() {
        return 0;
    }

    @Override
    public void post(Runnable runnable) {

    }
}
