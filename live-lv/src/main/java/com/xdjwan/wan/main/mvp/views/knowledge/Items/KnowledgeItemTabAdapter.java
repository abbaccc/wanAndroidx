package com.xdjwan.wan.main.mvp.views.knowledge.Items;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeItemTabAdapter extends FragmentPagerAdapter {
    private List<String> titles;
    private List<Integer> ids;
    private List<KnowledgeListFragment> knowledgeLIstFragments = new ArrayList<>();

    KnowledgeItemTabAdapter(FragmentManager fm, List<String> titles, List<Integer> idss) {
        super(fm);
        this.titles = titles;
        this.ids = idss;
        for (Integer id : ids) {
            knowledgeLIstFragments.add(KnowledgeListFragment.newInstance(id));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return knowledgeLIstFragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        return POSITION_NONE;
//    }
}
