package com.xdjwan.wan.main.mvp.views.knowledge.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xdjcore.core.MvpBase.MvpBaseFragment;
import com.xdjcore.core.ui.recycler.BaseDecoration;
import com.xdjwan.wan.R;
import com.xdjwan.wan.main.mvp.models.entity.KnowledgeBean;
import com.xdjwan.wan.main.mvp.precenters.knowledge.KnowledgePrecenter;
import com.xdjwan.wan.main.mvp.views.home.AritleHistorySearchFragment;
import com.xdjwan.wan.main.mvp.views.knowledge.Items.KonwledgeItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jx on 2018/5/3.
 */

public class KnowledgeFragment extends MvpBaseFragment<I_Knowledge, KnowledgePrecenter> implements I_Knowledge, OnRefreshListener, View.OnClickListener {
    private RecyclerView recyclerView;
    private Button btn_search;
    private RefreshLayout refreshLayout;
    private KnowledgeAdapter knowledgeAdapter;


    @Override
    public Object setLayout() {
        return R.layout.delegate_knowledge_tree;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        recyclerView = rootView.findViewById(R.id.id_knowledge_rlv);
        btn_search = rootView.findViewById(R.id.id_knowledge_search);
        btn_search.setOnClickListener(this);
        refreshLayout = rootView.findViewById(R.id.id_knowledge_refreshLayout);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);
        //
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.grey), 1));

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getPrecenter().getKnowledgeData();
    }

    @Override
    public int setTitleBar() {
        return R.id.toolbar;
    }

    @Override
    public int setStatusBarView() {
        return 0;
    }

    @Override
    public void post(Runnable runnable) {

    }


    @Override
    public KnowledgePrecenter setPrencenter() {
        return new KnowledgePrecenter();
    }

    @Override
    public void knowledgeData(String json) {
        List<KnowledgeBean> knowledgeBeans = KonwledgeDataConverter.create().setData(json).conver();
        if (knowledgeAdapter == null) {
            knowledgeAdapter = new KnowledgeAdapter(R.layout.item_knowledge_main, knowledgeBeans);
            recyclerView.setAdapter(knowledgeAdapter);
            knowledgeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    adapterClick(adapter, position);
                }
            });

        } else {
            knowledgeAdapter.setNewData(knowledgeBeans);
        }

    }

    private void adapterClick(BaseQuickAdapter adapter, int position) {
        //处理要传递过去的数据,List<String> names,List<Integer> ids,String knowledgename
        List<String> names = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        KnowledgeBean knowledge = (KnowledgeBean) adapter.getData().get(position);
        String knowledgename = knowledge.getName();
        for (KnowledgeBean.ChildrenBean children : knowledge.getChildren()) {
            names.add(children.getName());
            ids.add(children.getId());
        }
        //转跳
        getParentDelegate().getSupportDelegate().start(KonwledgeItemFragment.create(names, ids, knowledgename));

    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        getPrecenter().getKnowledgeData();
        refreshLayout.finishRefresh();
    }


    @Override
    public void onClick(View v) {
        getParentDelegate().getSupportDelegate().start(AritleHistorySearchFragment.create());
    }


}
