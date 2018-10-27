package com.xdjwan.wan.main.mvp.views.knowledge.Items;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xdjcore.core.fragments.WanAdFragment;
import com.xdjcore.core.fragments.web.WebFragment;
import com.xdjwan.wan.R;
import com.xdjwan.wan.main.mvp.models.EventBusManager;
import com.xdjwan.wan.main.mvp.views.home.AritleHistorySearchFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class KonwledgeItemFragment extends WanAdFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private TextView knowledgeName;
    private Button mhomesearch;
    private Button btn_back;
    //
    private static final String KNOWLEDGENAME = "knowledgename";
    private static final String KNOWLEDGEITEMNAME = "knowledgeitemname";
    private static final String KNOWLEDGEITEMID = "knowledgeitemid";
    //
    List<Integer> ids = new ArrayList<>();
    List<String> names = new ArrayList<>();
    String knowledgename;

    public static KonwledgeItemFragment create(List<String> names, List<Integer> ids, String knowledgename) {
        KonwledgeItemFragment konwledgeItemFragment = new KonwledgeItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KNOWLEDGENAME, knowledgename);
        bundle.putIntegerArrayList(KNOWLEDGEITEMID, (ArrayList<Integer>) ids);
        bundle.putStringArrayList(KNOWLEDGEITEMNAME, (ArrayList<String>) names);
        konwledgeItemFragment.setArguments(bundle);
        return konwledgeItemFragment;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_knowledge_item;
    }

    @Override
    public void initUI() {

    }




    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            names = getArguments().getStringArrayList(KNOWLEDGEITEMNAME);
            ids = getArguments().getIntegerArrayList(KNOWLEDGEITEMID);
            knowledgename = getArguments().getString(KNOWLEDGENAME);
        }
        initView(rootView);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private void initView(@NonNull View rootView) {
        btn_back = rootView.findViewById(R.id.id_back);
        mTabLayout = rootView.findViewById(R.id.id_knowledge_item_tab);
        mViewpager = rootView.findViewById(R.id.id_knowledge_item_viewpager);
        knowledgeName = rootView.findViewById(R.id.id_knowledge_item_name);
        mhomesearch = rootView.findViewById(R.id.id_knowledge_search);
        //使用getFragmentManager()会导致返回点击两次退出监听异常
        mViewpager.setAdapter(new KnowledgeItemTabAdapter(getChildFragmentManager(), names, ids));
        mViewpager.setOffscreenPageLimit(ids.size());
        mViewpager.setCurrentItem(0, false);
        mTabLayout.setupWithViewPager(mViewpager, true);
        knowledgeName.setText(knowledgename);
        //
        mhomesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getSupportDelegate().start(AritleHistorySearchFragment.create());
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void startWebFrament(EventBusManager busManager) {
        getSupportDelegate().start(WebFragment.create(busManager.getUrl()));
    }

}
