package com.xdjcore.core.net.interceptors;

import android.support.annotation.NonNull;

import com.xdjcore.core.app.ConfigKeys;
import com.xdjcore.core.ui.storage.LattePreference;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class SyncWebApiCookieToNativeInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        final String nativeCookie = LattePreference.getCustomAppProfile(ConfigKeys.COOKIE);
        if (nativeCookie != null) {
            builder.removeHeader("Cookie");
            builder.addHeader("Cookie", nativeCookie);
        }
        return chain.proceed(builder.build());
    }
}