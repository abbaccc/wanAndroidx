package com.xdjwan.wan.main.mvp.precenters.home;

import com.xdjcore.core.MvpBase.BasePrecenter;
import com.xdjwan.wan.main.mvp.models.home.AritleSearchModel;
import com.xdjwan.wan.main.mvp.models.home.I_HomeModelGetData;
import com.xdjwan.wan.main.mvp.views.home.I_HomeAritleSerach;

public class HomeAritleSearchPrecenter extends BasePrecenter<I_HomeAritleSerach> {

    public void searchAritleData(int indexPage, String searchText) {
        AritleSearchModel.create().searchData(indexPage, searchText, new I_HomeModelGetData() {
            @Override
            public void searchData(String data) {
                if (getmView() != null) {
                    getmView().AritleSearchDatas(data);
                }
            }
        });

    }


}
