package com.xdjwan.wan.main.mvp.precenters.home;

import com.xdjcore.core.MvpBase.BasePrecenter;
import com.xdjwan.wan.main.mvp.models.home.HomeAritleSortModel;
import com.xdjwan.wan.main.mvp.models.home.I_HomeAritleSortModel;
import com.xdjwan.wan.main.mvp.views.home.I_HomeAritleSort;

public class HomeAritleSortPrecenter extends BasePrecenter<I_HomeAritleSort> {

    public void getAritleSortDatas(int pageIndex, int cid) {
        new HomeAritleSortModel().getSortDatas(pageIndex, cid, new I_HomeAritleSortModel() {
            @Override
            public void sortData(String data) {
                if (getmView() != null) {
                    getmView().AritleSortDatas(data);
                }
            }
        });
    }


}
