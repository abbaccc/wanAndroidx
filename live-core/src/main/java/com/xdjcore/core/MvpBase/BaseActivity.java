package com.xdjcore.core.MvpBase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.xdjcore.core.ui.SystemStatusBar.systemStatusBar;


/**
 * 用于管理precenter的绑定解绑
 * Created by xdj on 2017/12/7.
 */

public abstract class BaseActivity<V extends BaseViewInterface, P extends BasePrecenter<V>> extends AppCompatActivity implements BaseViewInterface {
    private P precenter;
    private long exitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewID());
        //设置状态栏
        systemStatusBar.setSystemBar(this);
        if (precenter == null) {
            precenter = setPrencenter();
        }
        precenter.attach((V) this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (precenter != null) {
            precenter.dettach();
        }
    }

    public P getPrecenter() {
        return precenter;
    }

    public abstract P setPrencenter();

    public abstract int setContentViewID();


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "快速按两次退出",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
