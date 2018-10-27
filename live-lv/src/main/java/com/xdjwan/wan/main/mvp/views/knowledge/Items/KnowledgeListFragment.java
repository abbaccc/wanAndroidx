package com.xdjwan.wan.main.mvp.views.knowledge.Items;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xdjcore.core.MvpBase.MvpBaseFragment;
import com.xdjcore.core.ui.recycler.BaseDecoration;
import com.xdjcore.core.ui.recycler.MultipleItemEntity;
import com.xdjwan.wan.R;
import com.xdjwan.wan.main.mvp.models.EventBusManager;
import com.xdjwan.wan.main.mvp.precenters.knowledge.KnowledgeItemListPrecenter;
import com.xdjwan.wan.main.mvp.views.home.aritle.AritleDataConverter;
import com.xdjwan.wan.main.mvp.views.home.aritle.AritleDatasMultipleFields;
import com.xdjwan.wan.main.mvp.views.home.aritle.AritleSortRecycleAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class KnowledgeListFragment extends MvpBaseFragment<I_KnowledgeItemList, KnowledgeItemListPrecenter> implements I_KnowledgeItemList, OnRefreshListener {
    private static final String ID = "ids";
    private RecyclerView recyclerView;
    private int id;
    private AritleSortRecycleAdapter adapter;
    private RefreshLayout refreshLayout;


    public static KnowledgeListFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID, id);
        KnowledgeListFragment fragment = new KnowledgeListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public KnowledgeItemListPrecenter setPrencenter() {
        return new KnowledgeItemListPrecenter();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_knowledge_tree_item_list;
    }

    @Override
    public void initUI() {
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        refreshLayout = rootView.findViewById(R.id.id_knowledge_refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        recyclerView = rootView.findViewById(R.id.id_knowledge_rlv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.grey), 1));
        assert getArguments() != null;
        id = getArguments().getInt(ID);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //第一次提交获取数据
        getPrecenter().getItemKnowledgeData(0, id);
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
    public void knowledgeItemData(String json) {
        AritleDataConverter dataConverter = new AritleDataConverter();
        ArrayList<MultipleItemEntity> entityArrayList = dataConverter.setJsonData(json).convert();
        if (adapter == null) {
            adapter = new AritleSortRecycleAdapter(R.layout.item_home_aritle_sort, entityArrayList, this);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String url = ((MultipleItemEntity) adapter.getData().get(position)).getField(AritleDatasMultipleFields.LINK);
                    assert getParentFragment() != null;
                    EventBus.getDefault().post(new EventBusManager().setUrl(url));
                }
            });
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setNewData(entityArrayList);
        }

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh();
        getPrecenter().getItemKnowledgeData(0, id);
    }
}
