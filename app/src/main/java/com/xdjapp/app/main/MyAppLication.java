package com.xdjapp.app.main;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.xdjcore.core.app.Skylark;
import com.xdjcore.core.net.interceptors.DebugInterceptor;
import com.xdjwan.wan.datas.UrlTexts;
import com.xdjwan.wan.icon.FontEcModule;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * Created by jx on 2018/5/3.
 */

public class MyAppLication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Skylark.init(getApplicationContext())
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withApiHost(UrlTexts.BASE_URL)
                .configure();

        Fragmentation.builder()
                // show stack view. Mode: BUBBLE, SHAKE, NONE
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(me.yokeyword.fragmentation.BuildConfig.DEBUG)
                .install();

    }
}
