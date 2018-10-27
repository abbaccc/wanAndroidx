package com.xdjcore.core.fragments.web;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.PermissionInterceptor;
import com.xdjcore.core.R;
import com.xdjcore.core.fragments.WanAdFragment;

public class WebFragment extends WanAdFragment {
    private static final String TAG = "WebFragment";
    private AgentWeb mAgentWeb;

    public static WebFragment create(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(Contents.URL_KEY, url);
        WebFragment webFragment = new WebFragment();
        webFragment.setArguments(bundle);
        return webFragment;
    }


    @Override
    public Object setLayout() {
        return R.layout.fragment_web;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        Bundle bundle = getArguments();
        String url = bundle.getString(Contents.URL_KEY);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) rootView.findViewById(R.id.id_wb_ln), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .interceptUnkownUrl()
                .closeWebViewClientHelper()
                .createAgentWeb()
                .ready()
                .go(url);
        mAgentWeb.getAgentWebSettings().getWebSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放
        mAgentWeb.getAgentWebSettings().getWebSettings().setLoadWithOverviewMode(true); //设置充满全屏幕,电脑网页适配手机屏幕
        mAgentWeb.getAgentWebSettings().getWebSettings().setBuiltInZoomControls(true);
        //隐藏缩放控件
        mAgentWeb.getAgentWebSettings().getWebSettings().setDisplayZoomControls(false);


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
    public void onPause() {
        super.onPause();
        mAgentWeb.getWebLifeCycle().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAgentWeb.getWebLifeCycle().onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    protected PermissionInterceptor mPermissionInterceptor = new PermissionInterceptor() {

        /**
         * PermissionInterceptor 能达到 url1 允许授权， url2 拒绝授权的效果。
         * @param url
         * @param permissions
         * @param action
         * @return true 该Url对应页面请求权限进行拦截 ，false 表示不拦截。
         */
        @Override
        public boolean intercept(String url, String[] permissions, String action) {
            return false;
        }
    };


    @Override
    public boolean onBackPressedSupport() {
        if (mAgentWeb.getWebCreator().getWebView().canGoBack()) {
            mAgentWeb.getWebCreator().getWebView().goBack();
            return true;
        }
        return false;
    }
}
