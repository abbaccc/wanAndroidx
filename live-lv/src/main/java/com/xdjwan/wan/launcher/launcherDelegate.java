package com.xdjwan.wan.launcher;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.xdjcore.core.fragments.WanAdFragment;
import com.xdjcore.core.ui.launcher.ScrollLaunhcerTag;
import com.xdjcore.core.util.storage.SkylarkPreference;
import com.xdjcore.core.util.timer.BaseTimerTask;
import com.xdjcore.core.util.timer.ITimerListener;
import com.xdjwan.wan.R;
import com.xdjwan.wan.main.mvp.views.WanBottomDelegate;

import java.text.MessageFormat;
import java.util.Timer;


/**
 * 启动页
 * Created by jx on 2018/5/3.
 */

public class launcherDelegate extends WanAdFragment implements ITimerListener {
    AppCompatTextView mTextView = null;
    AppCompatTextView mTextViewClick;
    private Timer mTimer = null;
    private int mCount = 5;


    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTextView != null) {
                    mTextView.setText(MessageFormat.format("跳过\n{0}", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });


    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void initUI() {

    }

    @SuppressLint("CutPasteId")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mTextView = rootView.findViewById(R.id.tv_launcher_timer);
        mTextViewClick = rootView.findViewById(R.id.tv_launcher_timer);
        mTextViewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                    checkIsShowScroll();
                }
            }
        });
        initTimer();
    }

    private void checkIsShowScroll() {
        if (!SkylarkPreference.getAppFlag(ScrollLaunhcerTag.HAS_FIRST_LAUNCHER_APP.name())) {
            getSupportDelegate().startWithPop(new launcherScrollDelegate());
        } else {
            //检查用户登录了APP
            getSupportDelegate().startWithPop(new WanBottomDelegate());
        }
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public int setTitleBar() {
        return R.id.toolbar;
    }

    @Override
    public int setStatusBarView() {
        return 0;
    }


}
