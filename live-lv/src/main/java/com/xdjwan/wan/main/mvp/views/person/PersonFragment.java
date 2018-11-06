package com.xdjwan.wan.main.mvp.views.person;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.xdjcore.core.fragments.WanAdFragment;
import com.xdjcore.core.net.cookies.CookiesManager;
import com.xdjwan.wan.R;
import com.xdjwan.wan.datas.Constant;
import com.xdjwan.wan.main.mvp.models.EventBusManager;
import com.xdjwan.wan.main.mvp.views.person.login.LoginFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by jx on 2018/5/3.
 */

public class PersonFragment extends WanAdFragment {
    private TextView tv_name;
    private LinearLayout ln_login;
    private LinearLayout ln_collect;
    private LinearLayout ln_todo;
    private LinearLayout ln_setting;

    @Override
    public Object setLayout() {
        return R.layout.delegate_person;
    }

    @Override
    public void initUI() {
        setLogin();
        collectClick();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        EventBus.getDefault().register(this);
        tv_name = rootView.findViewById(R.id.id_person_userName);
        ln_login = rootView.findViewById(R.id.id_ln_login);
        ln_collect = rootView.findViewById(R.id.id_person_ln_collect);
        ln_todo = rootView.findViewById(R.id.id_person_ln_todo);
        ln_setting = rootView.findViewById(R.id.id_person_ln_setting);
        //
        if (SPUtils.getInstance(Constant.LOGIN_SPNAME).getBoolean(Constant.LOGIN_IS)) {
            tv_name.setText(SPUtils.getInstance(Constant.LOGIN_SPNAME).getString(Constant.LOGIN_NAME) + "");
        } else {
            tv_name.setText("点击登录");
        }

    }

    private void setLogin() {
        ln_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLogin = SPUtils.getInstance(Constant.LOGIN_SPNAME).getBoolean(Constant.LOGIN_IS);
                if (isLogin) {
                    //弹出登录退出框
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("提示")
                            .setMessage("是否退出当前账号？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CookiesManager.clearAllCookies();
                                    SPUtils.getInstance(Constant.LOGIN_SPNAME).clear();
                                    tv_name.setText("点击登录");
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .create().show();

                } else {
                    //登录
                    getParentDelegate().getSupportDelegate().start(LoginFragment.newInstance());
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setUserName(EventBusManager userName) {
        if (userName != null) {
            tv_name.setText(userName.getUserName() + "");
        }
    }

    private void collectClick() {
        ln_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
