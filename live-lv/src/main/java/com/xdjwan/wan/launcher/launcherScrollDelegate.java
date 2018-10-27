package com.xdjwan.wan.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.xdjcore.core.fragments.WanAdFragment;
import com.xdjcore.core.ui.launcher.ScrollLaunhcerTag;
import com.xdjcore.core.ui.launcher.launhcerHolderCreator;
import com.xdjcore.core.util.storage.SkylarkPreference;
import com.xdjwan.wan.R;
import com.xdjwan.wan.main.mvp.views.WanBottomDelegate;

import java.util.ArrayList;

/**
 * 第一次加载app启动介绍页
 * Created by jx on 2018/5/3.
 */
public class launcherScrollDelegate extends WanAdFragment implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private void initBanner() {
        INTEGERS.add(R.mipmap.lancherscroll01);
        INTEGERS.add(R.mipmap.lancherscroll02);
        INTEGERS.add(R.mipmap.lancherscroll03);
        mConvenientBanner
                .setPages(new launhcerHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_fouse})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
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
    public void onItemClick(int position) {
        //点击最后一个
        if (position == INTEGERS.size() - 1) {
            SkylarkPreference.setAppFlag(ScrollLaunhcerTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登陆了
            getSupportDelegate().startWithPop(new WanBottomDelegate());

        }
    }


    @Override
    public void post(Runnable runnable) {

    }
}
