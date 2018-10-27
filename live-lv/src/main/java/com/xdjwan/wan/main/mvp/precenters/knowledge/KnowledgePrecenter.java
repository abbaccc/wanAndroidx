package com.xdjwan.wan.main.mvp.precenters.knowledge;

import com.xdjcore.core.MvpBase.BasePrecenter;
import com.xdjwan.wan.main.mvp.models.konwledeg.I_KnowledgeModel;
import com.xdjwan.wan.main.mvp.models.konwledeg.KnowledgeModel;
import com.xdjwan.wan.main.mvp.views.knowledge.main.I_Knowledge;

public class KnowledgePrecenter extends BasePrecenter<I_Knowledge> {

    public void getKnowledgeData() {
        KnowledgeModel.create().getknowledgeData(new I_KnowledgeModel() {
            @Override
            public void knowledgeResponse(String json) {
                if (null != getmView()) {
                    getmView().knowledgeData(json);
                }
            }
        });
    }





}
