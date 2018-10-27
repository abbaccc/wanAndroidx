package com.xdjwan.wan.main.mvp.views.home.refresh;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.xdjcore.core.fragments.WanAdFragment;
import com.xdjcore.core.fragments.web.WebFragment;
import com.xdjcore.core.ui.recycler.DataConverter;
import com.xdjcore.core.ui.recycler.MultipleItemEntity;
import com.xdjwan.wan.main.mvp.models.entity.PagingBean;
import com.xdjwan.wan.main.mvp.views.home.aritle.AritleDataConverter;
import com.xdjwan.wan.main.mvp.views.home.aritle.AritleDatasMultipleFields;

import java.util.ArrayList;

//, OnLoadMoreListener
public class AritleRefreshHandler {
    private final WanAdFragment DELEGATE;
    private final RefreshLayout REFRESHLAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private final DataConverter CONVERTER;
    private BaseQuickAdapter mAdapter;
    private final ArrayList<MultipleItemEntity> list;

    public static AritleRefreshHandler create(RefreshLayout REFRESHLAYOUT, RecyclerView RECYCLERVIEW, WanAdFragment DELEGATE, BaseQuickAdapter mAdapter) {
        return new AritleRefreshHandler(REFRESHLAYOUT, new PagingBean(), RECYCLERVIEW, new AritleDataConverter(), DELEGATE, mAdapter);
    }

    public AritleRefreshHandler(RefreshLayout REFRESHLAYOUT, PagingBean BEAN, RecyclerView RECYCLERVIEW, DataConverter CONVERTER, WanAdFragment DELEGATE, BaseQuickAdapter mAdapter) {
        this.REFRESHLAYOUT = REFRESHLAYOUT;
        this.BEAN = BEAN;
        this.RECYCLERVIEW = RECYCLERVIEW;
        this.CONVERTER = CONVERTER;
        list = new ArrayList<>();
        this.DELEGATE = DELEGATE;
        this.mAdapter = mAdapter;
    }

    public int currentIndex() {
        return BEAN.getPageIndex();
    }

    public void firstPage(String jsonData) {
        if (jsonData == null || jsonData.equals("")) {
            return;
        }
        reset();
        final JSONObject object = JSON.parseObject(jsonData).getJSONObject("data");
        BEAN.setTotal(object.getInteger("total"))
                .setPageSize(object.getInteger("size"));
        //
        list.clear();
        CONVERTER.clearData();
        //
        list.addAll(CONVERTER.setJsonData(jsonData).convert());
        mAdapter.setNewData(list);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("click", position + "");
                MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
                String url = entity.getField(AritleDatasMultipleFields.LINK);
                DELEGATE.getSupportDelegate().start(WebFragment.create(url));
            }
        });
        RECYCLERVIEW.setAdapter(mAdapter);
        REFRESHLAYOUT.setEnableLoadMore(true);
        BEAN.addIndex();
        REFRESHLAYOUT.setEnableLoadMore(true);
    }


    public void reset() {
        BEAN.setCurrentCount(0);
        BEAN.setPageSize(0);
        BEAN.setTotal(0);
        BEAN.setmPageCount(0);
        BEAN.setPageIndex(0);
        BEAN.setDelayed(0);
    }


    public void loadMorePaging(String jsonData) {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        if (mAdapter.getData().size() < pageSize || currentCount >= total) {
            REFRESHLAYOUT.finishLoadMoreWithNoMoreData();
        } else {
            CONVERTER.clearData();
            list.addAll(CONVERTER.setJsonData(jsonData).convert());
            mAdapter.setNewData(list);
            //累加数量
            BEAN.setCurrentCount(mAdapter.getData().size());
            BEAN.addIndex();
            REFRESHLAYOUT.finishLoadMore();
        }
    }


}
