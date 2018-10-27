package com.xdjcore.core.MvpBase;

import android.util.Log;

/**
 * Created by xdj on 2017/12/7.
 */

public abstract class BasePrecenter<T extends BaseViewInterface> {

    private T mView;

    public T getmView() {
        return mView;
    }

    public void attach(T mView) {
        this.mView = mView;
        Log.d("Precenter,attach", this.mView.toString());
    }

    public void dettach() {
        mView = null;
    }


}
