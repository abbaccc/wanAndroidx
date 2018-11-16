package com.xdjwan.wan.main.mvp.models.home;

import android.annotation.SuppressLint;
import android.util.Log;

import com.xdjcore.core.net.RestClient;
import com.xdjcore.core.net.callback.IError;
import com.xdjcore.core.net.callback.IFailure;
import com.xdjcore.core.net.callback.ISuccess;
import com.xdjcore.core.net.rx.Exception.ResponseTransformer;
import com.xdjcore.core.net.rx.RxRestClient;
import com.xdjcore.core.net.rx.Schedulers.SchedulerProvider;
import com.xdjwan.wan.datas.UrlTexts;

import io.reactivex.functions.Consumer;
import retrofit2.Response;

public class HomeModel {
    private static final String TAG = "HomeModel";


    @SuppressLint("CheckResult")
    public void getBannerInfo(final I_HomeModel i_homeModel) {
        RxRestClient.builder()
                .url(UrlTexts.HOME_BANNER_URL)
                .build()
                .get()
                .compose(ResponseTransformer.handleResult())
                .compose(SchedulerProvider.getInstance().applySchedulers());
    }


    public void getArticleData(final int pagerPosition, final I_HomeModel i_homeModel) {
        String url = UrlTexts.HOME_ARTICLE_URL_FIRST_LIST + pagerPosition + UrlTexts.JSON;
        Log.e(TAG, "getArticleData:" + url);
        RestClient.builder().url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String s) {
                        i_homeModel.HomeData(s);
                        //    Log.e(TAG, "getArticleDataOnSuccess: " + s);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {
                        Log.e("ArticleData", "IError: " + code + "\tmessage:" + message);
                    }
                })
                .build()
                .get();
    }


}
