package com.xdjwan.wan.main.mvp.views.home.aritle;

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

public class AritleDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final int errorCode = JSON.parseObject(getmJsonData()).getInteger("errorCode");
        final String errorMsg = JSON.parseObject(getmJsonData()).getString("errorMsg");
        if (errorCode == 0) {
            final JSONObject data = JSON.parseObject(getmJsonData()).getJSONObject("data");
            final int curPage = data.getInteger("curPage");
            final int offset = data.getInteger("offset");
            final boolean over = data.getBoolean("over");
            final int pageCount = data.getInteger("pageCount");
            final int size = data.getInteger("size");
            final int total = data.getInteger("total");
            final JSONArray datas = data.getJSONArray("datas");
            for (int i = 0; i < datas.size(); i++) {
                final JSONObject jsonObject = datas.getJSONObject(i);
                final String apkLink = jsonObject.getString("apkLink");
                final String author = jsonObject.getString("author");
                final int chapterId = jsonObject.getInteger("chapterId");
                final String chapterName = jsonObject.getString("chapterName");
                final boolean collect = jsonObject.getBoolean("collect");
                final int courseId = jsonObject.getInteger("courseId");
                final String desc = jsonObject.getString("desc");
                final String envelopePic = jsonObject.getString("envelopePic");
                final boolean fresh = jsonObject.getBoolean("fresh");
                final int id = jsonObject.getInteger("id");
                final String link = jsonObject.getString("link");
                final String niceDate = jsonObject.getString("niceDate");
                final String origin = jsonObject.getString("origin");
                final String projectLink = jsonObject.getString("projectLink");
                final String publishTime = jsonObject.getString("publishTime");
                final int superChapterId = jsonObject.getInteger("superChapterId");
                final String superChapterName = jsonObject.getString("superChapterName");
                final String title = jsonObject.getString("title");
                final int type = jsonObject.getInteger("type");
                final int userId = jsonObject.getInteger("userId");
                final int visible = jsonObject.getInteger("visible");
                final int zan = jsonObject.getInteger("zan");
                final JSONArray tags = data.getJSONArray("tags");
                if (tags != null) {
                    for (int j = 0; j < tags.size(); i++) {
                        String TagName = tags.getJSONObject(i).getString("name");
                        String TagUrl = tags.getJSONObject(i).getString("url");
                    }
                }
                final MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                        .setField(AritleMultipleFields.CURPAGE, curPage)
                        .setField(AritleMultipleFields.OFFST, offset)
                        .setField(AritleMultipleFields.OVER, over)
                        .setField(AritleMultipleFields.PAGECOUNT, pageCount)
                        .setField(AritleMultipleFields.SIZE, size)
                        .setField(AritleMultipleFields.TOTAL, total)
                        .setField(AritleDatasMultipleFields.APKLINK, apkLink)
                        .setField(AritleDatasMultipleFields.AUTHOR, author)
                        .setField(AritleDatasMultipleFields.CHAPTERID, chapterId)
                        .setField(AritleDatasMultipleFields.CHAPTERNAME, chapterName)
                        .setField(AritleDatasMultipleFields.COLLECT, collect)
                        .setField(AritleDatasMultipleFields.COURSEID, courseId)
                        .setField(AritleDatasMultipleFields.DESC, desc)
                        .setField(AritleDatasMultipleFields.ENVELOPPIC, envelopePic)
                        .setField(AritleDatasMultipleFields.FRESH, fresh)
                        .setField(AritleDatasMultipleFields.ID, id)
                        .setField(AritleDatasMultipleFields.LINK, link)
                        .setField(AritleDatasMultipleFields.NICEDATE, niceDate)
                        .setField(AritleDatasMultipleFields.ORIGIN, origin)
                        .setField(AritleDatasMultipleFields.PROJECTLINK, projectLink)
                        .setField(AritleDatasMultipleFields.PUBLISHTIME, publishTime)
                        .setField(AritleDatasMultipleFields.SUPERCHAPTERID, superChapterId)
                        .setField(AritleDatasMultipleFields.SUPERCHAPTERNAME, superChapterName)
                        .setField(AritleDatasMultipleFields.TITLE, title)
                        .setField(AritleDatasMultipleFields.TYPE, type)
                        .setField(AritleDatasMultipleFields.USERID, userId)
                        .setField(AritleDatasMultipleFields.VISIBLE, visible)
                        .setField(AritleDatasMultipleFields.ZAN, zan)
                        .build();

                ENTITIES.add(itemEntity);
            }
        } else {
        }
        return ENTITIES;
    }

}
