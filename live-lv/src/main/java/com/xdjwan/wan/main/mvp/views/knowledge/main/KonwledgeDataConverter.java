package com.xdjwan.wan.main.mvp.views.knowledge.main;

import com.alibaba.fastjson.JSON;
import com.xdjwan.wan.main.mvp.models.entity.KnowledgeBean;

import java.util.ArrayList;
import java.util.List;

public class KonwledgeDataConverter {
    private List<KnowledgeBean> BEAN;
    private String json;

    public static KonwledgeDataConverter create() {
        return new KonwledgeDataConverter();
    }

    private KonwledgeDataConverter() {
        BEAN = new ArrayList<>();
    }

    public KonwledgeDataConverter setData(String json) {
        this.json = json;
        return this;
    }

    public List<KnowledgeBean> conver() {
        String data = JSON.parseObject(json).getJSONArray("data").toString();
        List<KnowledgeBean> knowledgeBeans = JSON.parseArray(data, KnowledgeBean.class);
        return knowledgeBeans;
    }


}
