package com.xdjwan.wan.datas;

/**
 * Created by jx on 2018/5/7.
 */

public class UrlTexts {
    public static final String BASE_URL = "http://www.wanandroid.com/";

    //1、
    // 首页banner
    public static final String HOME_BANNER_URL = "banner/json/";

    //2、
    // 首页文章地址HOME_ARTICLE_URL_FIRST+position+JSON
    public static final String HOME_ARTICLE_URL_FIRST_LIST = "article/list/";
    public static final String JSON = "/json";

    //3、
    // 在首页点击体系文章，http://www.wanandroid.com/article/list/0/json?cid=60
    //0为页码，cid为点击superChapterName时，对应是"superChapterId": 153,
    //点击"chapterName "时为chapterId":409,
    //搜索文章，http://www.wanandroid.com/article/query/0/json ，post 参数k
    public static final String HOME_ARTICLE_URL_FIRST_QUERY = "article/query/";
    //搜索热词，http://www.wanandroid.com/hotkey/json
    public static final String HOME_ARTICLE_URL_HOST_DATA = "hotkey/json/";

    //4、
    // 知识体 http://www.wanandroid.com/tree/json
    public static final String KNOWLEDGE_TREE = "tree/json";


    //5、
    // 登录http://www.wanandroid.com/user/login, 方法:POST,参数:username，password
    public static final String PERSON_LOGIN = "user/login";
    //退出,方法：GET,http://www.wanandroid.com/user/logout/json
    public static final String PERSON_UNLOGIN = "user/logout/json";
    //注册http://www.wanandroid.com/user/register,方法：POST,参数:username,password,repassword
    public static final String PERSON_REGIST = "user/register";


    /**
     * 6.1收藏站内文章 http://www.wanandroid.com/lg/collect/list/0/json ，方法：GET，参数： 页码：拼接在链接中，从0开始。
     */
    public static final String LG_COLLECT_LIST = "lg/collect/list/";


    /**
     * 收藏站内文章 http://www.wanandroid.com/lg/collect/1165/json,方法：POST,参数： 文章id，拼接在链接中。
     */
    public static final String LG_COLLECT = "lg/collect/";

    /**
     * 6.3收藏站外文章http://www.wanandroid.com/lg/collect/add/json,方法：POST,参数：title，author，link
     */
    public static final String LG_COLLECT_ADD = "lg/collect/add/json";

    //6.4 取消收藏,两个触发
    /**
     * 文章列表 http://www.wanandroid.com/lg/uncollect_originId/2333/json,方法：POST参数：id ,拼接在链接上
     */
    public static final String LG_UNCOLLECT_ORIGINID = "lg/uncollect_originId/";

    /**
     * 6.4.2我的收藏页面（该页面包含自己录入的内容） http://www.wanandroid.com/lg/uncollect/2805/json,
     * 方法：POST,参数：,id:拼接在链接上,originId:列表页下发，无则为-1
     */
    public static final String LG_UNCOLLECT = "lg/uncollect_originId/";

}
