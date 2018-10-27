package com.xdjcore.core.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.xdjcore.core.R;
import com.xdjcore.core.fragments.ExitforWaitDelegate;
import com.xdjcore.core.fragments.WanAdFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by jx on 2018/5/3.
 */

public abstract class BaseBottomDelegate extends ExitforWaitDelegate implements View.OnClickListener {
    private final ArrayList<WanAdFragment> ITEM_DELEGATES = new ArrayList<>();
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, WanAdFragment> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedCole = Color.parseColor("#666666");

    public abstract LinkedHashMap<BottomTabBean, WanAdFragment> setItems(ItemBuilder itemBuilder);

    LinearLayoutCompat mBottomBar = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }


    public abstract int setIndexDelegate();

    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedCole = setClickedColor();
        }

        //初始化获取到的Items数据
        final ItemBuilder builder = new ItemBuilder();
        final LinkedHashMap<BottomTabBean, WanAdFragment> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, WanAdFragment> item : ITEMS.entrySet()) {
            final BottomTabBean tabBean = item.getKey();
            final WanAdFragment itemDelegate = item.getValue();
            TAB_BEANS.add(tabBean);
            ITEM_DELEGATES.add(itemDelegate);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mBottomBar = rootView.findViewById(R.id.bottom_bar);
        //加载每个Ittem视图
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setText(bean.getICON());
            itemTitle.setText(bean.getTITLE());
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedCole);
                itemTitle.setTextColor(mClickedCole);
            }
        }
        //加载所有fragment视图
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);

    }


    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            item.setGravity(RelativeLayout.CENTER_IN_PARENT);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.parseColor("#666666"));
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.parseColor("#666666"));
        }
    }

    @Override
    public void onClick(View view) {
        final int tag = (int) view.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) view;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedCole);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedCole);

        //
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;

    }
}
