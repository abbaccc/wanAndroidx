package com.xdjwan.wan.main.mvp.models.home;

import android.util.Log;

import com.xdjcore.core.net.RestClient;
import com.xdjcore.core.net.callback.IError;
import com.xdjcore.core.net.callback.ISuccess;
import com.xdjwan.wan.datas.UrlTexts;

public class HomeAritleSortModel {
    private static final String TAG = "HomeAritleSortModel";

    public void getSortDatas(int pageIndex, int cid, final I_HomeAritleSortModel i_model) {
        String url = UrlTexts.HOME_ARTICLE_URL_FIRST_LIST + pageIndex + UrlTexts.JSON;
        RestClient.builder()
                .url(url)
                .params("cid", cid)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String s) {
                        Log.e(TAG, "onSuccessURL: " + s);
                        i_model.sortData(s);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                })
                .build().get();

    }


}
