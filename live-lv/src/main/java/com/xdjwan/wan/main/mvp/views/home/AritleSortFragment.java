package com.xdjwan.wan.main.mvp.views.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xdjcore.core.MvpBase.MvpBaseFragment;
import com.xdjcore.core.fragments.web.WebFragment;
import com.xdjcore.core.ui.recycler.BaseDecoration;
import com.xdjcore.core.ui.recycler.MultipleItemEntity;
import com.xdjwan.wan.R;
import com.xdjwan.wan.datas.Constant;
import com.xdjwan.wan.main.mvp.precenters.home.HomeAritleSortPrecenter;
import com.xdjwan.wan.main.mvp.views.home.aritle.AritleDataConverter;
import com.xdjwan.wan.main.mvp.views.home.aritle.AritleDatasMultipleFields;
import com.xdjwan.wan.main.mvp.views.home.aritle.AritleSortRecycleAdapter;

public class AritleSortFragment extends MvpBaseFragment<I_HomeAritleSort, HomeAritleSortPrecenter> implements I_HomeAritleSort {
    private final static String TAG = "AritleSortFragment";
    private Bundle bundle;
    private RecyclerView recyclerView;



    public static AritleSortFragment create(int pageIndex, int cid) {
        AritleSortFragment fragment = new AritleSortFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.PAGEINDEX_KEY, pageIndex);
        bundle.putInt(Constant.ARITLE_SORT_CID_KEY, cid);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_home_aritle_sort;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        int pageindex = bundle.getInt(Constant.PAGEINDEX_KEY);
        int cid = bundle.getInt(Constant.ARITLE_SORT_CID_KEY);
        getPrecenter().getAritleSortDatas(pageindex, cid);
    }

    @Override
    public void initUI() {

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        bundle = getArguments();
        recyclerView = rootView.findViewById(R.id.id_home_aritle_sort_rlv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.grey), 1));
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
    public HomeAritleSortPrecenter setPrencenter() {
        return new HomeAritleSortPrecenter();
    }

    @Override
    public void AritleSortDatas(String json) {
        AritleDataConverter dataConverter = new AritleDataConverter();
        AritleSortRecycleAdapter adapter = new AritleSortRecycleAdapter(R.layout.item_home_aritle_sort,
                dataConverter.setJsonData(json).convert(), this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e(TAG, "onItemClick: " + position);
                String url = ((MultipleItemEntity) adapter.getData().get(position)).getField(AritleDatasMultipleFields.LINK);
                getSupportDelegate().start(WebFragment.create(url));
            }
        });
        recyclerView.setAdapter(adapter);
    }


}
