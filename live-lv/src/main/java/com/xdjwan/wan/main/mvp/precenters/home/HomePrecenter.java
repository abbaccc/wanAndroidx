package com.xdjwan.wan.main.mvp.precenters.home;

import com.xdjcore.core.MvpBase.BasePrecenter;
import com.xdjwan.wan.main.mvp.models.home.HomeModel;
import com.xdjwan.wan.main.mvp.models.home.I_HomeModel;
import com.xdjwan.wan.main.mvp.views.home.I_Home;

public class HomePrecenter extends BasePrecenter<I_Home> {


    public void getBannerData() {
        // HomeModel homeModel = new HomeModel();
        new HomeModel().getBannerInfo(new I_HomeModel() {
            @Override
            public void HomeData(String s) {
                getmView().bannerMsg(s);
            }
        });
    }

    public void getarticles(int pagerPosition) {
        new HomeModel().getArticleData(pagerPosition, new I_HomeModel() {
            @Override
            public void HomeData(String s) {
                getmView().articleData(s);
            }
        });
    }

}
