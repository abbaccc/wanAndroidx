package com.xdjwan.wan.main.mvp.views.home.history;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xdjwan.wan.main.mvp.models.entity.TopSearchData;

import java.util.ArrayList;
import java.util.List;

public class HistoryDataConverter {
    private List<TopSearchData> topSearchDataList;
    private String json;

    public static HistoryDataConverter create() {
        return new HistoryDataConverter();
    }

    public HistoryDataConverter setData(String json) {
        topSearchDataList = new ArrayList<>();
        setJson(json);
        return this;
    }

    private String getJson() {
        return json;
    }

    private void setJson(String json) {
        this.json = json;
    }

    public List<TopSearchData> conver() {
        JSONArray data = JSON.parseObject(getJson()).getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonObject = data.getJSONObject(i);
            int id = jsonObject.getInteger("id");
            String link = jsonObject.getString("link");
            String name = jsonObject.getString("name");
            int order = jsonObject.getInteger("order");
            int visible = jsonObject.getInteger("visible");

            topSearchDataList.add(new TopSearchData()
                    .setId(id).setLink(link).setName(name).setOrder(order).setVisible(visible));

        }

        return topSearchDataList;
    }


}
