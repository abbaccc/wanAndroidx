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
import com.xdjcore.core.net.Entity.myBanners;
import com.xdjwan.wan.datas.UrlTexts;

import java.util.List;

import io.reactivex.functions.Consumer;

public class HomeModel {
    private static final String TAG = "HomeModel";


    @SuppressLint("CheckResult")
    public void getBannerInfo(final I_HomeModel i_homeModel) {

//        RxRestClient.builder()
//                .url(UrlTexts.HOME_BANNER_URL)
//                .build()
//                .getBanner()
//                .compose(ResponseTransformer.<List<myBanners>>handleResult())
//                .compose(SchedulerProvider.getInstance().<List<myBanners>>applySchedulers())
//                .subscribe(new Consumer<List<myBanners>>() {
//                    @Override
//                    public void accept(List<myBanners> myBanners) throws Exception {
//                        Log.e(TAG, "getBannerInfo: " + myBanners.toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });


        RestClient.builder()
                .url(UrlTexts.HOME_BANNER_URL)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        i_homeModel.HomeData(response);
                    }
                })
                .build()
                .get();


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
