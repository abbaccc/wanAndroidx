package com.xdjcore.core.net.rx.Exception;

import android.net.ParseException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

public class CustomException {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;

    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;

    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1002;

    /**
     * 协议错误
     */
    public static final int HTTP_ERROR = 1003;

    public static ApiException handleException(Throwable e) {
        ApiException exception;
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof JsonIOException
                ) {
            //解析错误
            exception = new ApiException(PARSE_ERROR, e.getMessage());
            return exception;
        } else if (e instanceof ConnectException) {
            //网络错误
            exception = new ApiException(NETWORK_ERROR, e.getMessage());
            return exception;
        } else if (e instanceof UnknownHostException || e instanceof SocketException) {
            //连接错误
            exception = new ApiException(HTTP_ERROR, e.getMessage());
            return exception;
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            exception = new ApiException(UNKNOWN, convertStatusCode(httpException));
            return exception;

        } else {
            exception = new ApiException(UNKNOWN, e.getMessage());
            return exception;
        }

    }

    private static String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() == 500) {
            msg = "服务器发生错误";
        } else if (httpException.code() == 404) {
            msg = "请求地址不存在";
        } else if (httpException.code() == 403) {
            msg = "请求被服务器拒绝";
        } else if (httpException.code() == 307) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }

}
