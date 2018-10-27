package com.xdjapp.app.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.xdjcore.core.activities.ProxyActivity;
import com.xdjcore.core.fragments.WanAdFragment;
import com.xdjwan.wan.launcher.launcherDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public WanAdFragment setRootDelegate() {
        return new launcherDelegate();
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
