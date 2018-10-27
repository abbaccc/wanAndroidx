package com.xdjwan.wan.main.mvp.models;

public class EventBusManager {
    private String url;
    private String userName;
    public String getUrl() {
        return url;
    }

    public EventBusManager setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public EventBusManager setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
