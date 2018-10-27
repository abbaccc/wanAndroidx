package com.xdjcore.core.MvpBase;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xdjcore.core.fragments.WanAdFragment;


/**
 * Created by xdj on 2017/12/20.
 */

public abstract class MvpBaseFragment<V extends BaseViewInterface, P extends BasePrecenter<V>> extends WanAdFragment implements BaseViewInterface {
    private P precenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (precenter == null) {
            precenter = setPrencenter();
        }
        precenter.attach((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (precenter != null) {
            precenter.dettach();
        }
    }

    public P getPrecenter() {
        return precenter;
    }

    public abstract P setPrencenter();

}
