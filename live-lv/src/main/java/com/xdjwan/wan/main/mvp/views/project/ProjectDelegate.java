package com.xdjwan.wan.main.mvp.views.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xdjcore.core.MvpBase.BasePrecenter;
import com.xdjcore.core.MvpBase.MvpBaseFragment;
import com.xdjwan.wan.R;

/**
 * Created by jx on 2018/5/3.
 */

public class ProjectDelegate extends MvpBaseFragment {
    @Override
    public Object setLayout() {
        return R.layout.delegate_person;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

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
    public BasePrecenter setPrencenter() {
        return null;
    }
}
