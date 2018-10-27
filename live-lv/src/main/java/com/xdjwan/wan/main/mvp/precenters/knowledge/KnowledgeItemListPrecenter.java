package com.xdjwan.wan.main.mvp.precenters.knowledge;

import com.xdjcore.core.MvpBase.BasePrecenter;
import com.xdjwan.wan.main.mvp.models.konwledeg.I_KnowledgeModel;
import com.xdjwan.wan.main.mvp.models.konwledeg.KnowledgeModel;
import com.xdjwan.wan.main.mvp.views.knowledge.Items.I_KnowledgeItemList;

public class KnowledgeItemListPrecenter extends BasePrecenter<I_KnowledgeItemList> {

    public void getItemKnowledgeData(int pageIndex, int cid) {
        KnowledgeModel.create().getknowledgeItemData(new I_KnowledgeModel() {
            @Override
            public void knowledgeResponse(String json) {
                if (null != getmView()) {
                    getmView().knowledgeItemData(json);
                }
            }
        }, pageIndex, cid);
    }


}
