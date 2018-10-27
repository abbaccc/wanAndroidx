package com.xdjwan.wan.main.mvp.models.entity;


public class TopSearchData {

    /**
     * 热词搜索的bean
     * "id": 6,
     * "link": "",
     * "name": "面试",
     * "order": 1,
     * "visible": 1
     */

    private int id;
    private String link;
    private String name;
    private int order;
    private int visible;

    public int getId() {
        return id;
    }

    public TopSearchData setId(int id) {
        this.id = id;
        return this;
    }

    public String getLink() {
        return link;
    }

    public TopSearchData setLink(String link) {
        this.link = link;
        return this;
    }

    public String getName() {
        return name;
    }

    public TopSearchData setName(String name) {
        this.name = name;
        return this;
    }

    public int getOrder() {
        return order;
    }

    public TopSearchData setOrder(int order) {
        this.order = order;
        return this;
    }

    public int getVisible() {
        return visible;
    }

    public TopSearchData setVisible(int visible) {
        this.visible = visible;
        return this;
    }
}
