package com.xdjwan.wan.main.mvp.views.person.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.xdjcore.core.fragments.WanAdFragment;
import com.xdjcore.core.net.RestClient;
import com.xdjcore.core.net.callback.IError;
import com.xdjcore.core.net.callback.ISuccess;
import com.xdjwan.wan.R;
import com.xdjwan.wan.datas.Constant;
import com.xdjwan.wan.datas.UrlTexts;
import com.xdjwan.wan.main.mvp.models.EventBusManager;
import com.xdjwan.wan.main.mvp.models.person.LoginMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.WeakHashMap;

public class LoginFragment extends WanAdFragment {
    private TextInputEditText tv_name;
    private TextInputEditText tv_pws;
    private Button bt_login;
    private Button bt_regist;


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_login;
    }

    @Override
    public void initUI() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .init();

        login();
        regist();

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        tv_name = rootView.findViewById(R.id.id_login_ed_name);
        tv_pws = rootView.findViewById(R.id.id_login_ed_pwd);
        bt_login = rootView.findViewById(R.id.loginbtn);
        bt_regist = rootView.findViewById(R.id.registerbtn);
    }

    private void login() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tv_name.getText().toString();
                String pwd = tv_pws.getText().toString();
                if (name.equals("") || pwd.equals("")) {
                    Toast.makeText(getContext(), "请先填入账号密码!", Toast.LENGTH_SHORT).show();
                    return;
                }
                WeakHashMap<String, Object> weakHashMap = new WeakHashMap<>();
                weakHashMap.put("username", name);
                weakHashMap.put("password", pwd);
                RestClient.builder()
                        .url(UrlTexts.PERSON_LOGIN)
                        .params(weakHashMap)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                Log.d("LoginFragment", response);
                                loginSet(response);
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String msg) {

                            }
                        })
                        .build()
                        .post();


//                        .subscribe(new Consumer<String>() {
//                            @Override
//                            public void accept(String s) throws Exception {
//                                Log.d("LoginFragment", s);
//                                loginSet(s);
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//
//                            }
//                        });


            }
        });

    }

    private void regist() {
        bt_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tv_name.getText().toString();
                String pwd = tv_pws.getText().toString();
                if (name.equals("") || pwd.equals("")) {
                    Toast.makeText(getContext(), "请先填入账号密码!", Toast.LENGTH_SHORT).show();
                    return;
                }
                WeakHashMap<String, Object> weakHashMap = new WeakHashMap<>();
                weakHashMap.put("username", name);
                weakHashMap.put("password", pwd);
                weakHashMap.put("repassword", pwd);
                RestClient.builder()
                        .url(UrlTexts.PERSON_REGIST)
                        .params(weakHashMap)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                Log.d("LoginFragment", response);
                                loginSet(response);
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String msg) {

                            }
                        })
                        .build()
                        .post();
            }
        });

    }


    private void loginSet(String json) {
        LoginMessage loginMessage = JSON.parseObject(json, LoginMessage.class);
        if (loginMessage.getErrorCode() == 0) {
            SPUtils.getInstance(Constant.LOGIN_SPNAME).put(Constant.LOGIN_IS, true);
            SPUtils.getInstance(Constant.LOGIN_SPNAME).put(Constant.LOGIN_NAME, loginMessage.getData().getUsername());
            SPUtils.getInstance(Constant.LOGIN_SPNAME).put(Constant.LOGIN_PWS, loginMessage.getData().getPassword());
            EventBus.getDefault().post(new EventBusManager().setUserName(loginMessage.getData().getUsername()));
            getSupportDelegate().pop();
        } else if (loginMessage.getErrorCode() == -1) {
            Toast.makeText(getContext(), loginMessage.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int setTitleBar() {
        return R.id.id_toobar;
    }

    @Override
    public int setStatusBarView() {
        return 0;
    }

    @Override
    public void post(Runnable runnable) {

    }
}
