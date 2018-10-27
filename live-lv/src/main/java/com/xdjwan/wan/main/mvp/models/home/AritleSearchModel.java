package com.xdjwan.wan.main.mvp.models.home;

import com.xdjcore.core.net.RestClient;
import com.xdjcore.core.net.callback.IError;
import com.xdjcore.core.net.callback.ISuccess;
import com.xdjwan.wan.datas.UrlTexts;

public class AritleSearchModel {

    public static AritleSearchModel create() {
        return new AritleSearchModel();
    }

    public void searchData(int indexPage, String searchText, final I_HomeModelGetData I_searchModel) {
        String url = UrlTexts.HOME_ARTICLE_URL_FIRST_QUERY + indexPage + UrlTexts.JSON;
        RestClient.builder()
                .url(url)
                .params("k", searchText)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        I_searchModel.searchData(response);
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


    public void searchHotData(final I_HomeModelGetData I_searchModel) {
        RestClient.builder()
                .url(UrlTexts.HOME_ARTICLE_URL_HOST_DATA)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        I_searchModel.searchData(response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build().get();
    }


}
