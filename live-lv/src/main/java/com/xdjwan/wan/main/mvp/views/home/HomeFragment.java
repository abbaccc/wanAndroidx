package com.xdjwan.wan.main.mvp.views.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xdjcore.core.MvpBase.MvpBaseFragment;
import com.xdjcore.core.ui.banner.Banners;
import com.xdjcore.core.ui.recycler.BaseDecoration;
import com.xdjcore.core.ui.recycler.MultipleItemEntity;
import com.xdjwan.wan.R;
import com.xdjwan.wan.main.mvp.precenters.home.HomePrecenter;
import com.xdjwan.wan.main.mvp.views.home.aritle.HomeRecycleAdapter;
import com.xdjwan.wan.main.mvp.views.home.banner.BannerListener;
import com.xdjwan.wan.main.mvp.views.home.banner.BannerMultipleFields;
import com.xdjwan.wan.main.mvp.views.home.banner.HomeBannerDataConverter;
import com.xdjwan.wan.main.mvp.views.home.refresh.AritleRefreshHandler;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends MvpBaseFragment<I_Home, HomePrecenter> implements I_Home {
    private Toolbar toolbar;
    private Banner banner;
    private RecyclerView rlv;
    private RefreshLayout smartRefreshLayout;
    private AritleRefreshHandler refreshHandler;
    private AppCompatEditText ed_search;


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initView(rootView);
    }

    private void initView(@NonNull View rootView) {
        toolbar = rootView.findViewById(R.id.tb_index);
        banner = rootView.findViewById(R.id.id_home_banner);
        rlv = rootView.findViewById(R.id.id_home_rlv);
        smartRefreshLayout = rootView.findViewById(R.id.refreshLayout);
        ed_search = rootView.findViewById(R.id.et_search_view);
        ed_search.setFocusable(false);
    }

    private void initBanner(List<MultipleItemEntity> bannerDatas) {
        List<String> urls = new ArrayList<>();
        for (MultipleItemEntity entity : bannerDatas) {
            String path = entity.getField(BannerMultipleFields.IMG).toString();
            Log.e("path", path);
            urls.add(path);
        }
        Banners<String> banners = new Banners<>();
        banners.setBanner(urls, banner, BannerListener.create(bannerDatas, getParentDelegate()));

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initSearch() {
        ed_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        getParentDelegate().getSupportDelegate().start(AritleHistorySearchFragment.create());
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public int setTitleBar() {
        return R.id.tb_index;
    }

    @Override
    public int setStatusBarView() {
        return 0;
    }


    @Override
    public void post(Runnable runnable) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getPrecenter().getBannerData();
        getPrecenter().getarticles(0);

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }

    @Override
    public void initUI() {
        initSearch();
        rlv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rlv.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.we_chat_black), 2));
        //
        refreshHandler = AritleRefreshHandler.create(smartRefreshLayout, rlv, getParentDelegate()
                , new HomeRecycleAdapter(R.layout.item_home_aritle, new ArrayList<MultipleItemEntity>(), getParentDelegate()));
        //
        initRefreshLayoutClick();

    }

    private void initRefreshLayoutClick() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                Log.e("onRefresh", "onRefresh: ");
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getPrecenter().getarticles(0);
                        refreshLayout.finishRefresh();
                    }
                }, 300);
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                Log.e("setOnLoadMoreListener", "setOnLoadMoreListener: ");
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getPrecenter().getarticles(refreshHandler.currentIndex());
                        refreshLayout.finishLoadMore();
                    }
                }, 300);
            }
        });
    }

    @Override
    public HomePrecenter setPrencenter() {
        return new HomePrecenter();
    }


    @Override
    public void bannerMsg(String msg) {
        HomeBannerDataConverter homeBannerDataConverter = new HomeBannerDataConverter();
        List<MultipleItemEntity> bannerDatas = homeBannerDataConverter.setJsonData(msg).convert();
        initBanner(bannerDatas);
    }

    @Override
    public void articleData(String jsonData) {
        int currentPage = JSON.parseObject(jsonData).getJSONObject("data").getInteger("curPage");
        if (currentPage == 1) {
            refreshHandler.firstPage(jsonData);
        } else if (currentPage > 1) {
            refreshHandler.loadMorePaging(jsonData);
        }
    }


}
