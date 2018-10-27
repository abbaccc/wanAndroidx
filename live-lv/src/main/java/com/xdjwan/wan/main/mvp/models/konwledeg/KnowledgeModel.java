package com.xdjwan.wan.main.mvp.models.konwledeg;

import android.util.Log;

import com.xdjcore.core.net.RestClient;
import com.xdjcore.core.net.callback.IError;
import com.xdjcore.core.net.callback.ISuccess;
import com.xdjwan.wan.datas.UrlTexts;

public class KnowledgeModel {
    private static final String TAG = "KnowledgeModel";

    public static KnowledgeModel create() {
        return new KnowledgeModel();
    }

    public void getknowledgeData(final I_KnowledgeModel knowledgeModel) {
        RestClient.builder()
                .url(UrlTexts.KNOWLEDGE_TREE)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        knowledgeModel.knowledgeResponse(response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }


    public void getknowledgeItemData(final I_KnowledgeModel knowledgeModel, int pageIndex, int cid) {
        String url = UrlTexts.HOME_ARTICLE_URL_FIRST_LIST + pageIndex + UrlTexts.JSON;
        RestClient.builder()
                .url(url)
                .params("cid", cid)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String s) {
                        Log.e(TAG, "onSuccessURL: " + s);
                        knowledgeModel.knowledgeResponse(s);
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
