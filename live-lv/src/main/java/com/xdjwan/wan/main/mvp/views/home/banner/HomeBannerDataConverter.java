package com.xdjwan.wan.main.mvp.views.home.banner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xdjcore.core.ui.recycler.DataConverter;
import com.xdjcore.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * 解析数据的类
 * Created by jx on 2018/5/7.
 */

public class HomeBannerDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final int errorCode = JSON.parseObject(getmJsonData()).getInteger("errorCode");
        final String errorMsg = JSON.parseObject(getmJsonData()).getString("errorMsg");
        if (errorCode == 0) {
            final JSONArray dataArray = JSON.parseObject(getmJsonData()).getJSONArray("data");
            for (int i = 0; i < dataArray.size(); i++) {
                final JSONObject data = dataArray.getJSONObject(i);

                final String desc = data.getString("desc");
                final int id = data.getInteger("id");
                final String imagePath = data.getString("imagePath");
                final int isVisible = data.getInteger("isVisible");
                final int order = data.getInteger("order");
                final String title = data.getString("title");
                final int type = data.getInteger("type");
                final String url = data.getString("url");

                final MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                        .setField(BannerMultipleFields.DESC, desc)
                        .setField(BannerMultipleFields.ID, id)
                        .setField(BannerMultipleFields.IMG, imagePath)
                        .setField(BannerMultipleFields.ISVISIBLE, isVisible)
                        .setField(BannerMultipleFields.ORDER, order)
                        .setField(BannerMultipleFields.TITLE, title)
                        .setField(BannerMultipleFields.TYPE, type)
                        .setField(BannerMultipleFields.URL, url)
                        .build();
                ENTITIES.add(itemEntity);
            }
        } else {

        }
        return ENTITIES;
    }
//	         "desc": "一起来做个App吧",
//            "id": 10,
//            "imagePath": "http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
//            "isVisible": 1,
//            "order": 2,
//            "title": "一起来做个App吧",
//            "type": 0,
//            "url": "http://www.wanandroid.com/blog/show/2"
}
