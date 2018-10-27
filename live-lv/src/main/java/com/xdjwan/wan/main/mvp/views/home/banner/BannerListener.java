package com.xdjwan.wan.main.mvp.views.home.banner;

import android.util.Log;

import com.xdjcore.core.fragments.WanAdFragment;
import com.xdjcore.core.fragments.web.WebFragment;
import com.xdjcore.core.ui.recycler.MultipleItemEntity;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

public class BannerListener implements OnBannerListener {
    private List<MultipleItemEntity> bannerDatas;
    private WanAdFragment DELEGATE;

    public static BannerListener create(List<MultipleItemEntity> bannerDatas, WanAdFragment DELEGATE) {
        return new BannerListener(bannerDatas, DELEGATE);
    }

    public BannerListener(List<MultipleItemEntity> bannerDatas, WanAdFragment delegate) {
        this.bannerDatas = bannerDatas;
        this.DELEGATE = delegate;
    }

    @Override
    public void OnBannerClick(int position) {
        Log.d("BannerListener", "OnBannerClick: " + position);
        String url = bannerDatas.get(position).getField(BannerMultipleFields.URL);
        if (!url.equals("")) {
            DELEGATE.getSupportDelegate().start(WebFragment.create(url));
        }

    }


}
