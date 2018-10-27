package com.xdjwan.wan.main.mvp.views.home.aritle;

import com.alibaba.fastjson.JSON;
import com.xdjcore.core.net.RestClient;
import com.xdjcore.core.net.callback.IError;
import com.xdjcore.core.net.callback.ISuccess;
import com.xdjwan.wan.datas.UrlTexts;

public class Collect {
    private int pageId;

    public static Collect create() {
        return new Collect();
    }


    public void collectAritle(int pageId, final ICollect iCollect) {
        RestClient.builder()
                .url(UrlTexts.LG_COLLECT + pageId + UrlTexts.JSON)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        int errorCode = JSON.parseObject(response).getInteger("errorCode");
                        String errorMsg = JSON.parseObject(response).getString("errorMsg");
                        if (errorCode == 0) {
                            iCollect.isCollect(true, errorMsg);
                        } else {
                            iCollect.isCollect(false, errorMsg);
                        }
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


    public void unCollectAritle(int pageId, final ICollect iCollect) {
        RestClient.builder()
                .url(UrlTexts.LG_UNCOLLECT_ORIGINID + pageId + UrlTexts.JSON)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        int errorCode = JSON.parseObject(response).getInteger("errorCode");
                        String errorMsg = JSON.parseObject(response).getString("errorMsg");
                        if (errorCode == 0) {
                            iCollect.isCollect(true, errorMsg);
                        } else {
                            iCollect.isCollect(false, errorMsg);
                        }
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

    public interface ICollect {
        void isCollect(boolean is, String msg);
    }


}
