package com.xdjwan.wan.main.mvp.views.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xdjcore.core.MvpBase.MvpBaseFragment;
import com.xdjcore.core.ui.recycler.BaseDecoration;
import com.xdjcore.core.ui.recycler.MultipleItemEntity;
import com.xdjwan.wan.R;
import com.xdjwan.wan.datas.Constant;
import com.xdjwan.wan.main.mvp.precenters.home.HomeAritleSearchPrecenter;
import com.xdjwan.wan.main.mvp.views.home.aritle.HomeRecycleAdapter;
import com.xdjwan.wan.main.mvp.views.home.refresh.AritleRefreshHandler;

import java.util.ArrayList;

public class AritleSearchFragment extends MvpBaseFragment<I_HomeAritleSerach, HomeAritleSearchPrecenter> implements I_HomeAritleSerach {
    private static final String TAG = "AritleSearchFragment";
    private EditText ed_search;
    private RecyclerView rlv;
    private Button back;
    private Button btn_search;
    private RefreshLayout refreshLayout;
    private AritleRefreshHandler refreshHandler;

    public static AritleSearchFragment create(String hotName) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARITLE_SEARCH_HOT_NAME, hotName);
        AritleSearchFragment aritleSearchFragment = new AritleSearchFragment();
        aritleSearchFragment.setArguments(bundle);
        return aritleSearchFragment;
    }

    @Override
    public HomeAritleSearchPrecenter setPrencenter() {
        return new HomeAritleSearchPrecenter();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_aritle_search;
    }

    @Override
    public void initUI() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().pop();
            }
        });
        search();

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initView(rootView);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(ed_search, 0);//显示输入法
        //
        rlv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rlv.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.we_chat_black), 2));
        //
        refreshHandler = AritleRefreshHandler.create(refreshLayout, rlv,
                this, new HomeRecycleAdapter(R.layout.item_home_aritle, new ArrayList<MultipleItemEntity>(), this));
        initRefreshLayoutClick();

        //获取出传递过得来查询值
        Bundle bundle = getArguments();
        String hotName = bundle != null ? bundle.getString(Constant.ARITLE_SEARCH_HOT_NAME) : null;
        //设置Editext的值
        if (hotName != null || !hotName.equals("")) {
            ed_search.setText(hotName.trim());
            //获取数据请求
            getPrecenter().searchAritleData(0, hotName.trim());
        }



    }

    private void initView(@NonNull View rootView) {
        refreshLayout = rootView.findViewById(R.id.refreshLayout);
        back = rootView.findViewById(R.id.id_back);
        ed_search = rootView.findViewById(R.id.id_ed_search);
        rlv = rootView.findViewById(R.id.id_home_aritle_search_rlv);
        btn_search = rootView.findViewById(R.id.id_btn_search);
        //
        ed_search.setFocusable(true);//设置输入框可聚集
        ed_search.setFocusableInTouchMode(true);//设置触摸聚焦
        ed_search.requestFocus();//请求焦点
        ed_search.findFocus();//获取焦点
    }

    private void search() {
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = ed_search.getText().toString();
                if (!str.equals("")) {
                    //执行查询的网络操作
                    getPrecenter().searchAritleData(0, str);
                } else {
                    Toast.makeText(getContext(), "请输入搜索", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void initRefreshLayoutClick() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                Log.e("onRefresh", "onRefresh: ");
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String ed_text = ed_search.getText().toString();
                        if (!ed_text.equals("")) {
                            getPrecenter().searchAritleData(0, ed_text);
                        } else {
                            Toast.makeText(getContext(), "请输入搜索", Toast.LENGTH_SHORT).show();
                        }
                        refreshLayout.finishRefresh();
                    }
                }, 300);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                Log.e("setOnLoadMoreListener", "setOnLoadMoreListener: ");
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String ed_text = ed_search.getText().toString();
                        if (!ed_text.equals("")) {
                            getPrecenter().searchAritleData(refreshHandler.currentIndex(), ed_text);
                            refreshLayout.finishLoadMore();
                        }
                    }
                }, 300);
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
    public void AritleSearchDatas(String jsonData) {
        int currentPage = JSON.parseObject(jsonData).getJSONObject("data").getInteger("curPage");
        if (currentPage == 1) {
            refreshHandler.firstPage(jsonData);
        } else if (currentPage > 1) {
            refreshHandler.loadMorePaging(jsonData);
        }
    }


}
