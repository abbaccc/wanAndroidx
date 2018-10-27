package com.xdjwan.wan.main.mvp.precenters.home;

import com.xdjcore.core.MvpBase.BasePrecenter;
import com.xdjwan.wan.main.mvp.models.home.AritleSearchModel;
import com.xdjwan.wan.main.mvp.models.home.I_HomeModelGetData;
import com.xdjwan.wan.main.mvp.views.I_HistorySearch;

public class HomeAritleHistorySearchPrecenter extends BasePrecenter<I_HistorySearch> {

    public void getHotSearchData() {
        AritleSearchModel.create()
                .searchHotData(new I_HomeModelGetData() {
                    @Override
                    public void searchData(String data) {
                        if (getmView() != null) {
                            getmView().getHistroyData(data);
                        }
                    }
                });
    }

}
