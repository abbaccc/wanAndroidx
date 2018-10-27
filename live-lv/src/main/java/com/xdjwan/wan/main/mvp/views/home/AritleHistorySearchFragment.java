package com.xdjwan.wan.main.mvp.views.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xdjcore.core.MvpBase.MvpBaseFragment;
import com.xdjcore.core.ui.recycler.BaseDecoration;
import com.xdjwan.wan.R;
import com.xdjwan.wan.main.mvp.models.entity.TopSearchData;
import com.xdjwan.wan.main.mvp.precenters.home.HomeAritleHistorySearchPrecenter;
import com.xdjwan.wan.main.mvp.views.I_HistorySearch;
import com.xdjwan.wan.main.mvp.views.home.history.HistoryDataAdapter;
import com.xdjwan.wan.main.mvp.views.home.history.HistoryDataConverter;
import com.xdjwan.wan.main.mvp.views.home.history.HistoryUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

//所需工作
//1.通过接口获取搜索热词，填充到flowerLayout
//2.点击热词，转跳到搜索页面并且进行搜索，搜索页面的editext赋值，保存搜索的热词并刷新rlv的数据源显示
//3.点击搜索历史，转跳到搜索页面并且进行搜索，搜索页面的editext赋值
public class AritleHistorySearchFragment extends MvpBaseFragment<I_HistorySearch, HomeAritleHistorySearchPrecenter> implements I_HistorySearch {
    private TagFlowLayout mTopSearchFlowLayout;
    private RecyclerView recyclerView;
    private HistoryDataAdapter historyDataAdapter;
    private EditText editText;
    private Button btn_search;
    private Button btn_back;

    public static AritleHistorySearchFragment create() {
        return new AritleHistorySearchFragment();
    }


    @Override
    public HomeAritleHistorySearchPrecenter setPrencenter() {
        return new HomeAritleHistorySearchPrecenter();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_aritle_history_search;
    }

    @Override
    public void initUI() {
        initRecycleView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mTopSearchFlowLayout = rootView.findViewById(R.id.id_history_flowlayout);
        recyclerView = rootView.findViewById(R.id.id_history_search_rlv);
        editText = rootView.findViewById(R.id.id_ed_history_search);
        btn_search = rootView.findViewById(R.id.id_btn_history_search);
        btn_back = rootView.findViewById(R.id.id_back);
        getPrecenter().getHotSearchData();
        btn_search();
    }

    private void btn_search() {
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if (!name.equals("")) {
                    HistoryUtil.saveSearchHistory(name);
                    historyDataAdapter.setNewData(HistoryUtil.getSearchHistory());
                    getSupportDelegate().start(AritleSearchFragment.create(name));
                } else {
                    Toast.makeText(getContext(), "先输入数据啦~", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().pop();
            }
        });

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
    public void getHistroyData(String json) {
        final List<TopSearchData> topSearchDataList = HistoryDataConverter.create().setData(json).conver();
        mTopSearchFlowLayout.setAdapter(new TagAdapter<TopSearchData>(topSearchDataList) {
            @Override
            public View getView(FlowLayout parent, int position, TopSearchData topSearchData) {
                TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flower_textview, parent, false);
                assert topSearchDataList != null;
                final String name = topSearchData.getName();
                textView.setText(name);

                mTopSearchFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        String name = topSearchDataList.get(position).getName();
                        HistoryUtil.saveSearchHistory(name);
                        historyDataAdapter.setNewData(HistoryUtil.getSearchHistory());
                        getSupportDelegate().start(AritleSearchFragment.create(name));
                        return true;
                    }
                });

                return textView;
            }
        });
    }

    private void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.grey), 1));
        historyDataAdapter = new HistoryDataAdapter(R.layout.item_home_airtle_history_search, HistoryUtil.getSearchHistory(), this);
        recyclerView.setAdapter(historyDataAdapter);

    }


}
